package com.fadinglight.billsystem.data;

import com.fadinglight.billsystem.data.BillItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class BillItemTest {

    @Test
    void test() {
        var year = 2021;
        var month = 1;
        var day = 12;
        var name = "早餐";
        var money = 12;
        var cls = "吃饭";

        BillItem bill = new BillItem(year, month, day, name, new BigDecimal(money));
        bill.addCls(cls);
        assert bill.belongTo(cls);
        System.out.println(bill);

    }
}