package com.fadinglight.billsystem.data;

import com.fadinglight.billsystem.data.BillItem;

import java.io.IOException;
import java.util.List;

public interface BillDataSource {
    List<BillItem> searchBill(int year, int month) throws IOException;
}
