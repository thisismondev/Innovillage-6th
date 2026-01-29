package id.co.mondo.ukhuwah.data.utils

import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

fun calculateDateInMonth(date: String): String? {
    if (date.isBlank() || date == "-") return null

    return try {
        val localDate = LocalDate.parse(date.take(10))
        localDate.month.getDisplayName(TextStyle.FULL, Locale("id", "ID"))
    } catch (e: Exception) {
        null
    }
}