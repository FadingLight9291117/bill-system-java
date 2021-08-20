package com.fadinglight.billsystem.data

import java.math.BigDecimal
import java.time.LocalDate

data class BillItem(
    val date: LocalDate,
    val name: String,
    val money: BigDecimal,
    var cls: String? = null
) {
    companion object {
        @JvmStatic
        fun fromString(str: String): BillItem {
            val billItems = str.strip().split(" ")
            val year = billItems[0].toInt()
            val month = billItems[1].toInt()
            val day = billItems[2].toInt()
            val name = billItems[3]
            val date = LocalDate.of(year, month, day)
            val money = billItems[4].toBigDecimal()
            val cls = when (billItems.size) {
                5 -> null
                6 -> billItems[5]
                else -> throw IllegalArgumentException()
            }
            return BillItem(date, name, money, cls)
        }
    }
}


//fun main() {
//    val str = "2021 12 12 早餐 12.12"
//    val str2 = "$str 饮食"
//    val b1 = BillItem.fromString(str)
//    val b2 = BillItem.fromString(str2)
//
//    println(b1)
//    println(b2)
//}