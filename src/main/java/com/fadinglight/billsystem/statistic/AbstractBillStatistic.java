package com.fadinglight.billsystem.statistic;

import com.fadinglight.billsystem.data.BillDataSource;

import java.math.BigDecimal;
import java.util.List;

public abstract class AbstractBillStatistic {
    private final BillDataSource dataSource;

    public AbstractBillStatistic(BillDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract BigDecimal getMonthTotalMoney(int year, int month);

    public abstract List<BigDecimal> getPerDayMoney(int year, int month);

    public abstract List<BigDecimal> getPerMonthMoney(int year);
}
