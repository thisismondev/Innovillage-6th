package id.co.mondo.ukhuwah.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd", Locale("id"))
    return formatter.format(Date(millis))
}

fun formatDateToMonthYear(date: String?): Pair<String, String>? {
    if (date.isNullOrBlank() || date.length < 7) return null

    val year = date.substring(0, 4)
    val month = date.substring(5, 7)

    return year to month
}

