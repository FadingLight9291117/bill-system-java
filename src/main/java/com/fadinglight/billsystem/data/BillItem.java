package com.fadinglight.billsystem.data;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class BillItem {
    private final LocalDate data;
    private final String name;
    private final BigDecimal money;
    private final List<String> cls;

    public BillItem(int year, int month, int day, String name, BigDecimal money) {
        this.data = LocalDate.of(year, month, day);
        this.name = name;
        this.money = money;
        this.cls = new ArrayList<>();
    }

    public static BillItem fromString(String str) {
        var bill = str.split("\\s+");
        var year = Integer.parseInt(bill[0]);
        var month = Integer.parseInt(bill[1]);
        var day = Integer.parseInt(bill[2]);
        var name = bill[3];
        BigDecimal money = null;
        money = new BigDecimal(bill[4]);
        return new BillItem(year, month, day, name, money);

    }

    public void addCls(String cls) {
        if (cls != null) {
            this.cls.add(cls);
        }
    }

    public void clearCls() {
        this.cls.clear();
    }

    public boolean belongTo(String cls) {
        if (cls == null) {
            return false;
        }
        return this.cls.contains(cls);
    }
}
