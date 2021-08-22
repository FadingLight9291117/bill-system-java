package com.fadinglight.billsystem.statistic;

import com.fadinglight.billsystem.data.BillDataSource;
import com.fadinglight.billsystem.data.BillItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public abstract class AbstractBillStatistic {
    private final BillDataSource dataSource;

    protected AbstractBillStatistic(BillDataSource dataSource) {
        this.dataSource = dataSource;
    }

    // 钱总数
    public abstract BigDecimal getTotalMoneyOfYear(int year);

    public abstract BigDecimal getTotalMoneyOfMonth(int year, int month);

    public abstract BigDecimal getTotalMoneyOfDay(int year, int month, int day);

    // 钱列表
    public abstract List<BigDecimal> getPerMoneyOfMonth(int year);

    public abstract List<BigDecimal> getPerMoneyOfDay(int year, int month);

    public abstract List<BigDecimal> getPerMoneyOfDay(int year, int month, int day);


    // 根据类别分类金额
    public abstract Map<String, BigDecimal> getMoneyByClsOfYear(int year);

    public abstract Map<String, BigDecimal> getMoneyByClsOfMonth(int year, int month);

    public abstract Map<String, BigDecimal> getMoneyByClsOfDay(int year, int month, int day);

    // 开销项目
    public abstract List<BillItem> getItemsOfYear(int year);

    public abstract List<BillItem> getItemsOfMonth(int year, int month);

    public abstract List<BillItem> getItemsOfDay(int year, int month, int day);

    // 根据类别分类项目
    public abstract Map<String, List<BillItem>> getItemsByClsOfYear(int year);

    public abstract Map<String, List<BillItem>> getItemsByClsOfMonth(int year, int month);

    public abstract Map<String, List<BillItem>> getItemsByClsOfDay(int year, int month, int day);

    // 关于类别，获取所有大类类别
    public abstract List<String> getAllCls();

    // 获取指定大类的所有小类类别
    public abstract List<String> getAllSubClsOfCls(String cls);

    // 大类中小类金额统计
    public abstract Map<String, BigDecimal> getMoneyBySubClsOfYear(int year, String cls);

    public abstract Map<String, BigDecimal> getMoneyBySubClsOfMonth(int year, int month, String cls);

    public abstract Map<String, BigDecimal> getMoneyBySubClsOfDay(int year, int month, int day, String cls);
}
