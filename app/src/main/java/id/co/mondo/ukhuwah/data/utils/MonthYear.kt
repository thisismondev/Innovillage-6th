package id.co.mondo.ukhuwah.data.utils

import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Locale

fun getAvailableMonthsByYear(year: String): List<String> {
    val now = LocalDate.now()
    val selectedYear = year.toInt()

    val maxMonth =
        if (selectedYear == now.year) now.monthValue else 12

    return (1..maxMonth).map { month ->
        Month.of(month)
            .getDisplayName(TextStyle.FULL, Locale("id", "ID"))
    }
}

fun getAvailableYears(endYear: Int = 2025): List<String> {
    val currentYear = LocalDate.now().year
    return (currentYear downTo endYear).map { it.toString() }
}