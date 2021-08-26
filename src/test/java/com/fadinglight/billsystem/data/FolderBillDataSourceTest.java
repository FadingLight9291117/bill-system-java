package com.fadinglight.billsystem.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


class FolderBillDataSourceTest {
    FolderBillDataSource dataSource = new FolderBillDataSource("datasource.yaml");
    int year = 2021;
    int month = 10;
    int day = 21;

    FolderBillDataSourceTest() throws IOException {
        System.out.println(dataSource);
    }

    void printBill(List<BillItem> data) {
        for (var bill : data) {
            System.out.println(bill);
        }
    }

    @Test
    void searchBill() throws IOException {
        var data = dataSource.searchBill(year);
        printBill(data);
    }

    @Test
    void testSearchBill() throws IOException {
        var data = dataSource.searchBill(year, month);
        printBill(data);
    }

    @Test
    void testSearchBill1() throws IOException {
        var data = dataSource.searchBill(year, month, day);
        printBill(data);
    }

    @Test
    void getAllCls() {
        var data = dataSource.getAllCls();
        for (var entry : data.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }
}