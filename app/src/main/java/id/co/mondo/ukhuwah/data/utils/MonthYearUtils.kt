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