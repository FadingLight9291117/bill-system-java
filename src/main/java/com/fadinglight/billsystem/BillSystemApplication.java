package com.fadinglight.billsystem;

import com.fadinglight.billsystem.data.BillItem;
import com.fadinglight.billsystem.statistic.AbstractBillStatistic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController("/")
@SpringBootApplication
public class BillSystemApplication {

    private AbstractBillStatistic billStatistic;

    public BillSystemApplication() {

    }

    @GetMapping("/money")
    public BigDecimal getMoney(int year, int month) {
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(BillSystemApplication.class, args);
    }

}
