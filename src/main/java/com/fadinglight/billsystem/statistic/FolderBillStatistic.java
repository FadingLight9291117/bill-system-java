package com.fadinglight.billsystem.statistic;

import com.fadinglight.billsystem.data.BillDataSource;
import com.fadinglight.billsystem.data.BillItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class FolderBillStatistic extends AbstractBillStatistic {
    protected FolderBillStatistic(BillDataSource dataSource) {
        super(dataSource);
    }

    @Override
    public BigDecimal getTotalMoneyOfYear(int year) {
        return null;
    }

    @Override
    public BigDecimal getTotalMoneyOfMonth(int year, int month) {
        return null;
    }

    @Override
    public BigDecimal getTotalMoneyOfDay(int year, int month, int day) {
        return null;
    }

    @Override
    public List<BigDecimal> getPerMoneyOfMonth(int year) {
        return null;
    }

    @Override
    public List<BigDecimal> getPerMoneyOfDay(int year, int month) {
        return null;
    }

    @Override
    public List<BigDecimal> getPerMoneyOfDay(int year, int month, int day) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMoneyByClsOfYear(int year) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMoneyByClsOfMonth(int year, int month) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMoneyByClsOfDay(int year, int month, int day) {
        return null;
    }

    @Override
    public List<BillItem> getItemsOfYear(int year) {
        return null;
    }

    @Override
    public List<BillItem> getItemsOfMonth(int year, int month) {
        return null;
    }

    @Override
    public List<BillItem> getItemsOfDay(int year, int month, int day) {
        return null;
    }

    @Override
    public Map<String, List<BillItem>> getItemsByClsOfYear(int year) {
        return null;
    }

    @Override
    public Map<String, List<BillItem>> getItemsByClsOfMonth(int year, int month) {
        return null;
    }

    @Override
    public Map<String, List<BillItem>> getItemsByClsOfDay(int year, int month, int day) {
        return null;
    }

    @Override
    public List<String> getAllCls() {
        return null;
    }

    @Override
    public List<String> getAllSubClsOfCls(String cls) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMoneyBySubClsOfYear(int year, String cls) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMoneyBySubClsOfMonth(int year, int month, String cls) {
        return null;
    }

    @Override
    public Map<String, BigDecimal> getMoneyBySubClsOfDay(int year, int month, int day, String cls) {
        return null;
    }
}
