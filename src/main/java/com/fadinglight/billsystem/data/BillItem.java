package com.fadinglight.billsystem.data;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BillItem {
    final private LocalDate date;
    final private String name;
    final private BigDecimal money;
    private String cls;

    public static BillItem fromString(String str) {
        var itemStrs = str.strip().split("\\s+");
        var year = Integer.parseInt(itemStrs[0]);
        var month = Integer.parseInt(itemStrs[1]);
        var day = Integer.parseInt(itemStrs[2]);
        var date = LocalDate.of(year, month, day);
        var name = itemStrs[3];
        var money = new BigDecimal(itemStrs[4]);
        String cls;
        if (itemStrs.length == 5)
            cls = null;
        else if (itemStrs.length == 6)
            cls = itemStrs[5];
        else
            throw new IllegalArgumentException();

        return new BillItem(date, name, money, cls);
    }
}
