package id.co.mondo.ukhuwah.data.utils

import id.co.mondo.ukhuwah.data.model.AgeResult
import java.time.LocalDate
import java.time.Period

fun calculateAgeFromBirthToNow(
    birth: String
): AgeResult? {

    if (birth.isBlank() || birth == "-") return null

    val birthDate = LocalDate.parse(birth.take(10))
    val today = LocalDate.now()

    val period = Period.between(birthDate, today)

    return AgeResult(
        years = period.years,
        months = period.months,
        days = period.days
    )
}