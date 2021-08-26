package com.fadinglight.billsystem.statistic;

import com.fadinglight.billsystem.data.BillDataSource;
import com.fadinglight.billsystem.data.BillItem;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public abstract class AbstractBillStatistic {
    protected final BillDataSource dataSource;

    protected AbstractBillStatistic(BillDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 钱总数
    public abstract BigDecimal getTotalMoneyOfYear(int year) throws IOException;

    public abstract BigDecimal getTotalMoneyOfMonth(int year, int month) throws IOException;

    public abstract BigDecimal getTotalMoneyOfDay(int year, int month, int day) throws IOException;

    // 钱列表
    public abstract Map<Integer, BigDecimal> getPerMoneyOfMonth(int year) throws IOException;

    public abstract Map<Integer, BigDecimal> getPerMoneyOfDay(int year, int month) throws IOException;



    // 根据类别分类金额
    public abstract Map<String, BigDecimal> getMoneyByClsOfYear(int year) throws IOException;

    public abstract Map<String, BigDecimal> getMoneyByClsOfMonth(int year, int month) throws IOException;

    public abstract Map<String, BigDecimal> getMoneyByClsOfDay(int year, int month, int day) throws IOException;

    // 开销项目
    public abstract List<BillItem> getItemsOfYear(int year) throws IOException;

    public abstract List<BillItem> getItemsOfMonth(int year, int month) throws IOException;

    public abstract List<BillItem> getItemsOfDay(int year, int month, int day) throws IOException;

    // 根据类别分类项目
    public abstract Map<String, List<BillItem>> getItemsByClsOfYear(int year) throws IOException;

    public abstract Map<String, List<BillItem>> getItemsByClsOfMonth(int year, int month) throws IOException;

    public abstract Map<String, List<BillItem>> getItemsByClsOfDay(int year, int month, int day) throws IOException;

    // 关于类别，获取所有大类加小类类别
    public abstract Map<String, List<String>> getAllCls();


    // 大类中小类金额统计
    public abstract Map<String, BigDecimal> getMoneyBySubClsOfYear(int year, String cls) throws IOException;

    public abstract Map<String, BigDecimal> getMoneyBySubClsOfMonth(int year, int month, String cls) throws IOException;

    public abstract Map<String, BigDecimal> getMoneyBySubClsOfDay(int year, int month, int day, String cls) throws IOException;
}
