package com.fadinglight.billsystem.statistic;

import com.fadinglight.billsystem.data.BillDataSource;
import com.fadinglight.billsystem.data.BillItem;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BillStatistic extends AbstractBillStatistic {
    public BillStatistic(BillDataSource dataSource) {
        super(dataSource);
    }

    @NotNull
    private Map<Integer, BigDecimal> getMoneyOptionalMap(Map<Integer, List<BillItem>> itemMap) {
        Map<Integer, BigDecimal> result = new HashMap<>(itemMap.keySet().size());
        for (var entry : itemMap.entrySet()) {
            var totalMoney = entry.getValue()
                    .parallelStream()
                    .map(BillItem::getMoney)
                    .reduce(new BigDecimal(0), BigDecimal::add);
            result.put(entry.getKey(), totalMoney);
        }
        return result;
    }

    @NotNull
    private Map<String, BigDecimal> getStringBigDecimalMap(Map<String, List<BillItem>> items) {
        var res = new HashMap<String, BigDecimal>(items.keySet().size());
        for (var entry : items.entrySet()) {
            var money = entry.getValue()
                    .parallelStream()
                    .map(BillItem::getMoney)
                    .reduce(new BigDecimal(0), BigDecimal::add);
            res.put(entry.getKey(), money);
        }
        return res;
    }

    @Override
    public BigDecimal getTotalMoneyOfYear(int year) throws IOException {
        return dataSource.searchBill(year)
                .parallelStream()
                .map(BillItem::getMoney)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public BigDecimal getTotalMoneyOfMonth(int year, int month) throws IOException {
        return dataSource.searchBill(year, month)
                .parallelStream()
                .map(BillItem::getMoney)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public BigDecimal getTotalMoneyOfDay(int year, int month, int day) throws IOException {
        return dataSource.searchBill(year, month, day)
                .parallelStream()
                .map(BillItem::getMoney)
                .reduce(new BigDecimal(0), BigDecimal::add);
    }

    @Override
    public Map<Integer, BigDecimal> getPerMoneyOfMonth(int year) throws IOException {
        var itemMap = dataSource.searchBill(year)
                .parallelStream()
                .collect(Collectors.groupingBy(item -> item.getDate().getMonth().getValue()));
        return getMoneyOptionalMap(itemMap);
    }

    @Override
    public Map<Integer, BigDecimal> getPerMoneyOfDay(int year, int month) throws IOException {
        var itemMap = dataSource.searchBill(year, month)
                .parallelStream()
                .collect(Collectors.groupingBy(item -> item.getDate().getDayOfMonth()));
        return getMoneyOptionalMap(itemMap);
    }


    @Override
    public Map<String, BigDecimal> getMoneyByClsOfYear(int year) throws IOException {
        var items = dataSource.searchBill(year)
                .parallelStream()
                .collect(Collectors.groupingBy(BillItem::getCls));
        return getStringBigDecimalMap(items);
    }

    @Override
    public Map<String, BigDecimal> getMoneyByClsOfMonth(int year, int month) throws IOException {
        var items = dataSource.searchBill(year, month)
                .parallelStream()
                .collect(Collectors.groupingBy(BillItem::getCls));
        return getStringBigDecimalMap(items);
    }


    @Override
    public Map<String, BigDecimal> getMoneyByClsOfDay(int year, int month, int day) throws IOException {
        var items = dataSource.searchBill(year, month, day)
                .parallelStream()
                .collect(Collectors.groupingBy(BillItem::getCls));
        return getStringBigDecimalMap(items);
    }

    @Override
    public List<BillItem> getItemsOfYear(int year) throws IOException {
        return dataSource.searchBill(year);
    }

    @Override
    public List<BillItem> getItemsOfMonth(int year, int month) throws IOException {
        return dataSource.searchBill(year, month);
    }

    @Override
    public List<BillItem> getItemsOfDay(int year, int month, int day) throws IOException {
        return dataSource.searchBill(year, month, day);
    }

    @Override
    public Map<String, List<BillItem>> getItemsByClsOfYear(int year) throws IOException {
        return dataSource.searchBill(year)
                .parallelStream()
                .collect(Collectors.groupingBy(BillItem::getCls));
    }

    @Override
    public Map<String, List<BillItem>> getItemsByClsOfMonth(int year, int month) throws IOException {
        return dataSource.searchBill(year, month)
                .parallelStream()
                .collect(Collectors.groupingBy(BillItem::getCls));
    }

    @Override
    public Map<String, List<BillItem>> getItemsByClsOfDay(int year, int month, int day) throws IOException {
        return dataSource.searchBill(year, month, day)
                .parallelStream()
                .collect(Collectors.groupingBy(BillItem::getCls));
    }

    @Override
    public Map<String, List<String>> getAllCls() {
        return dataSource.getAllCls();
    }

    @Override
    public Map<String, BigDecimal> getMoneyBySubClsOfYear(int year, String cls) throws IOException {
        var items = dataSource.searchBill(year)
                .parallelStream()
                .filter(item -> item.getCls().equals(cls))
                .collect(Collectors.groupingBy(BillItem::getName));
        return getStringBigDecimalMap(items);
    }

    @Override
    public Map<String, BigDecimal> getMoneyBySubClsOfMonth(int year, int month, String cls) throws IOException {
        var items = dataSource.searchBill(year, month)
                .parallelStream()
                .filter(item -> item.getCls().equals(cls))
                .collect(Collectors.groupingBy(BillItem::getName));
        return getStringBigDecimalMap(items);
    }

    @Override
    public Map<String, BigDecimal> getMoneyBySubClsOfDay(int year, int month, int day, String cls) throws IOException {
        var items = dataSource.searchBill(year, month, day)
                .parallelStream()
                .filter(item -> item.getCls().equals(cls))
                .collect(Collectors.groupingBy(BillItem::getName));
        return getStringBigDecimalMap(items);
    }
}
