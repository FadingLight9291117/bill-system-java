package com.fadinglight.billsystem.data;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@EqualsAndHashCode
public class FolderBillDataSource implements BillDataSource {

    private final String billDirPath;
    private final List<Path> files;
    private final Map<String, Object> configMap;
    private int tmpYear;
    private int tmpMonth;

    private static Map<String, Object> parseYamlFile(String filepath) {
        var yaml = new Yaml();
        var inputStream = FolderBillDataSource.class.getClassLoader().getResourceAsStream(filepath);
        return yaml.load(inputStream);
    }


    public FolderBillDataSource(String configFile) throws IOException {
        this.configMap = parseYamlFile(configFile);

        this.billDirPath = (String) this.configMap.get("billPath");

        this.files = Files.list(Paths.get(this.billDirPath))
                .parallel()
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toList());
    }

    @Override
    public List<BillItem> searchBill(int year, int month) throws IOException {
        this.tmpYear = year;
        this.tmpMonth = month;
        Path filepath = null;
        for (var p : files) {
            var pNames = p.getFileName().toString().split("\\.");
            var pYear = Integer.parseInt(pNames[0]);
            var pMonth = Integer.parseInt(pNames[1]);
            if (pYear == year && pMonth == month) {
                filepath = p;
            }
        }
        if (filepath == null) {
            return Collections.emptyList();
        }

        Map<String, List<String>> classes = (Map<String, List<String>>) this.configMap.get("classes");

        var content = Files.readString(filepath);
        return fileStringToBillBlock(content)
                .flatMap(this::billBlockToBillItems)
                .map(billItem -> this.setBillCls(billItem, classes))
                .parallel()
                .collect(Collectors.toList());
    }

    private BillItem setBillCls(BillItem bill, Map<String, List<String>> classes) {
        var billName = bill.getName();
        for (var cls : classes.keySet()) {
            var items = classes.get(cls);
            if (items.contains(billName)) {
                bill.setCls(cls);
                break;
            }
        }
        return bill;
    }


    /**
     * 把每日的bill字符串块转化为BillItem对象列表
     */
    private Stream<BillItem> billBlockToBillItems(String block) {
        var lines = block.split("\r\n");
        var date = lines[0].strip().split("\\.");
        var day = date[date.length - 1];
        return Stream.of(lines)
                .skip(1)
                .map(String::strip)
                .map(str -> this.tmpYear + " " + this.tmpMonth + " " + day + " " + str)
                .map(BillItem::fromString);
    }

    /**
     * 把一整个文件的字符串转变为 每天的bill字符串块
     */
    private Stream<String> fileStringToBillBlock(String txt) {
        return Arrays.stream(txt.strip().split("\r\n\r\n"))
                .map(String::strip);

    }
}
