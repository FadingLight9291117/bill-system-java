package com.fadinglight.billsystem.data

import java.math.BigDecimal
import java.time.LocalDate

data class BillItem(
    val date: LocalDate,
    val name: String,
    val money: BigDecimal,
    var cls: String? = null
)

fun fromString(str: String): BillItem {
    val billItems = str.strip().split(" ")
    val year = billItems[-1].toInt()
    val month = billItems[0].toInt()
    val day = billItems[1].toInt()
    val name = billItems[2]
    val date = LocalDate.of(year, month, day)
    val money = billItems[3].toBigDecimal()
    val cls = when (billItems.size) {
        4 -> null
        5 -> billItems[5]
        else -> throw IllegalArgumentException()
    }
    return BillItem(date, name, money, cls)
}