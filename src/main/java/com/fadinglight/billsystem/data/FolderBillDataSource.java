package com.fadinglight.billsystem.data;


import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@ConfigurationProperties(prefix = "folder-bill-datasource")
@ToString
@EqualsAndHashCode
public class FolderBillDataSource implements BillDataSource {

    private final String billDirPath;
    private final List<Path> files;
    private int tmpYear;
    private int tmpMonth;

    @Autowired
    public FolderBillDataSource(BillFolderConfig folderConfig) throws IOException {
        this.billDirPath = folderConfig.getPath();
        this.files = Files.list(Paths.get(this.billDirPath))
                .parallel()
                .filter(p -> p.toString().endsWith(".txt"))
                .collect(Collectors.toList());
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

        var ctt = Files.readString(filepath);
        return fileStringToBillBlock(ctt)
                .flatMap(this::billBlockToBillItems)
                .parallel()
                .collect(Collectors.toList());
    }
}
