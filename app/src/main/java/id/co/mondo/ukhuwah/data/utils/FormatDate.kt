package id.co.mondo.ukhuwah.data.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale("id"))
    return formatter.format(Date(millis))
}