package com.fadinglight.billsystem.statistic;

import com.fadinglight.billsystem.data.BillDataSource;
import com.fadinglight.billsystem.data.FolderBillDataSource;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BillStatisticTest {
    AbstractBillStatistic billStatistic = new BillStatistic(new FolderBillDataSource("datasource.yaml"));

    int year = 2021;
    int month = 7;
    int day = 21;

    BillStatisticTest() throws IOException {
    }

    @Test
    void getTotalMoneyOfYear() throws IOException {
        billStatistic.getTotalMoneyOfYear(year);
    }

    @Test
    void getTotalMoneyOfMonth() throws IOException {
        billStatistic.getTotalMoneyOfMonth(year, month);
    }

    @Test
    void getTotalMoneyOfDay() throws IOException {
        billStatistic.getTotalMoneyOfDay(year, month ,day);
    }

    @Test
    void getPerMoneyOfMonth() {
    }

    @Test
    void getPerMoneyOfDay() {
    }

    @Test
    void getMoneyByClsOfYear() {
    }

    @Test
    void getMoneyByClsOfMonth() {
    }

    @Test
    void getMoneyByClsOfDay() {
    }

    @Test
    void getItemsOfYear() {
    }

    @Test
    void getItemsOfMonth() {
    }

    @Test
    void getItemsOfDay() {
    }

    @Test
    void getItemsByClsOfYear() {
    }

    @Test
    void getItemsByClsOfMonth() {
    }

    @Test
    void getItemsByClsOfDay() {
    }

    @Test
    void getAllCls() {
    }

    @Test
    void getMoneyBySubClsOfYear() {
    }

    @Test
    void getMoneyBySubClsOfMonth() {
    }

    @Test
    void getMoneyBySubClsOfDay() {
    }
}