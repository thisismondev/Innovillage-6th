package id.co.mondo.ukhuwah.data.utils

import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun calculateAgeInMonths(
    birth: String
): String?{
    if (birth.isBlank() || birth == "-") return null

    val birthDate = LocalDate.parse(birth.take(10))
    val today = LocalDate.now()

    return ChronoUnit.MONTHS.between(birthDate, today).toString()
}