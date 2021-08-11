package com.fadinglight.billsystem.data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FolderBillDataSourceTest {

    @Autowired
    FolderBillDataSource folderBillDataSource;

    @Test
    void searchBill() {
        System.out.println(folderBillDataSource);
    }

    @Test
    void testSearchBill() throws IOException {
        var s = folderBillDataSource.searchBill(2021, 6);
        System.out.println(s);
    }

    @Test
    void fileStringToMap() throws IOException {
        Path p = Paths.get("F://账单/2021.6.txt");
        var txt = Files.readString(p);
        System.out.println(txt);
        var block = Arrays.stream(txt.strip().split("\r\n\r\n"))
                .parallel()
                .map(String::strip)
                .map(str -> Arrays.asList(str.split("\r\n")))
                .collect(Collectors.toUnmodifiableMap(
                                list -> list.get(0),
                                list -> list.subList(1, list.size())
                        )
                );
        System.out.println(block);
    }
}