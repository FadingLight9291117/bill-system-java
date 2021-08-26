package com.fadinglight.billsystem.data;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface BillDataSource {
    List<BillItem> searchBill(int year) throws IOException;
    List<BillItem> searchBill(int year, int month) throws IOException;
    List<BillItem> searchBill(int year, int month, int day) throws IOException;
    Map<String, List<String>> getAllCls();
}
