package com.fadinglight.billsystem.data;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FolderBillDataSourceTest {

    @Test
    void test() throws IOException {
        FolderBillDataSource f = new FolderBillDataSource("datasource.yaml");
        System.out.println(f);
        var data = f.searchBill(2021, 6);
        for (var bill : data) {
            System.out.println(bill);
        }
    }

}