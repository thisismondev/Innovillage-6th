package id.co.mondo.ukhuwah.data.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.YearMonth

object MonthYearUtils {

    val monthNames = listOf(
        "Januari", "Februari", "Maret", "April",
        "Mei", "Juni", "Juli", "Agustus",
        "September", "Oktober", "November", "Desember"
    )

    @RequiresApi(Build.VERSION_CODES.O)
    fun currentYearMonth(): YearMonth = YearMonth.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun yearList(startYear: Int): List<Int> {
        return (startYear..currentYearMonth().year).toList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun monthList(selectedYear: Int): List<String> {
        return if (selectedYear == currentYearMonth().year) {
            monthNames.take(currentYearMonth().monthValue)
        } else {
            monthNames
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun yearListFromBirth(birth: String?): List<Int> {
        if (birth.isNullOrBlank() || birth.length < 7) return emptyList()

        val birthYear = birth.substring(0, 4).toInt()
        val currentYear = currentYearMonth().year

        return (birthYear..currentYear).toList()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun monthListFromBirth(
        birth: String?,
        selectedYear: Int
    ): List<String> {
        if (birth.isNullOrBlank() || birth.length < 7) {
            return monthNames
        }

        val birthYear = birth.substring(0, 4).toInt()
        val birthMonth = birth.substring(5, 7).toInt()

        val startMonth =
            if (selectedYear == birthYear) birthMonth else 1

        return monthNames
            .subList(startMonth - 1, monthNames.size)
    }



    @RequiresApi(Build.VERSION_CODES.O)
    fun resolveYearMonth(
        selected: YearMonth,
        newYear: Int
    ): YearMonth {
        val current = currentYearMonth()

        return if (newYear == current.year) {
            YearMonth.of(newYear, current.monthValue)
        } else {
            YearMonth.of(newYear, selected.monthValue)
        }
    }

}