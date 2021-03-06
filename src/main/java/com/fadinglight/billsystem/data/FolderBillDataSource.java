package com.fadinglight.billsystem.data;


import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@EqualsAndHashCode
public class FolderBillDataSource implements BillDataSource {

    private final String billDirPath;
    private final List<Path> files;
    private final Map<String, Object> configMap;


    public FolderBillDataSource(String configFile) throws IOException {
        this.configMap = parseYamlFile(configFile);

        this.billDirPath = (String) this.configMap.get("billPath");
        this.files = Files.list(Paths.get(this.billDirPath))
                .parallel()
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toList());
    }


    private Map<String, List<String>> getCls() {
        return (Map<String, List<String>>) this.configMap.get("classes");
    }


    private List<BillItem> searchBill(Path path) throws IOException {
        final var filename = path.getFileName().toString();
        final var names = filename.split("\\.");
        final var year = names[0];
        final var month = names[1];

        return fileStringToBillBlock(Files.readString(path))
                .flatMap(block -> billBlockToBillItems(block, year, month))
                .map(billItem -> this.setBillCls(billItem, this.getCls()))
                .collect(Collectors.toList());
    }

    @Override
    public List<BillItem> searchBill(int year) throws IOException {
        final var needPath = new ArrayList<Path>();
        for (var p : this.files) {
            var name = p.getFileName();
            var nYear = Integer.parseInt(name.toString().split("\\.")[0]);
            if (nYear == year) {
                needPath.add(p);
            }
        }
        final var res = new ArrayList<BillItem>(needPath.size());
        for (var path : needPath) {
            res.addAll(this.searchBill(path));
        }
        return res;
    }

    @Override
    public List<BillItem> searchBill(int year, int month) throws IOException {
        var filePath = Paths.get(this.billDirPath, year + "." + month + ".txt");
        if (!this.files.contains(filePath)) {
            return Collections.emptyList();
        }
        return this.searchBill(filePath);
    }

    @Override
    public List<BillItem> searchBill(int year, int month, int day) throws IOException {
        return searchBill(year, month)
                .parallelStream()
                .filter(item -> item.getDate().getDayOfMonth() == day)
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<String>> getAllCls() {
        return this.getCls();
    }

    private BillItem setBillCls(BillItem bill, Map<String, List<String>> classes) {
        var billName = bill.getName();
        for (var entry : classes.entrySet()) {
            var clsName = entry.getKey();
            var items = entry.getValue();
            if (items.contains(billName)) {
                bill.setCls(clsName);
                break;
            }
        }
        if (bill.getCls() == null) {
            bill.setCls("??????");
        }
        return bill;
    }

    /*
     * ??????3?????????????????????
     * 1. ?????? 12 -> ??????
     * 2. ??????12  -> ????????????????????????????????????
     * 3. ??????    -> ?????????????????????
     * */
    private String dealExceptItemString(String str) {
        String name;
        String money;
        String p = "(\\D+)(\\d+\\.*\\d*)";
        Pattern r = Pattern.compile(p);
        Matcher m = r.matcher(str);
        if (m.find()) {
            name = m.group(1);
            money = m.group(2);
        } else {
            name = str.strip();
            money = "0";
        }
        return name + " " + money;
    }

    /**
     * ????????????bill?????????????????????BillItem????????????
     */
    private Stream<BillItem> billBlockToBillItems(String block, String year, String month) {
        var lines = block.split("\r\n");
        var date = lines[0].strip().split("\\.");
        var day = date[date.length - 1];
        return Stream.of(lines)
                .skip(1)
                .map(this::dealExceptItemString)
                .map(str -> year + " " + month + " " + day + " " + str)
                .map(BillItem::fromString);
    }

    /**
     * ??????????????????????????????????????? ?????????bill????????????
     */
    private Stream<String> fileStringToBillBlock(String txt) {
        return Arrays.stream(txt.strip().split("\r\n\r\n"))
                .map(String::strip);
    }

    private static Map<String, Object> parseYamlFile(String filepath) {
        var yaml = new Yaml();
        var inputStream = FolderBillDataSource.class.getClassLoader().getResourceAsStream(filepath);
        return yaml.load(inputStream);
    }
}
