package id.co.mondo.ukhuwah.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.utils.MonthYearUtils
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.FilterDropdown
import id.co.mondo.ukhuwah.ui.components.HistoryCard
import id.co.mondo.ukhuwah.ui.components.SelectChildren
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {

    val currentDate = MonthYearUtils.currentYearMonth()
    var selectedDate by remember { mutableStateOf(currentDate) }

    val yearList = MonthYearUtils.yearList(2024)
    val monthList = MonthYearUtils.monthList(selectedDate.year)


    var selectedChild by remember { mutableStateOf("Semua") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        AppTopBar(
            title = "Riwayat Pertumbuhan",
            showBack = false
        )
        SelectChildren(
            selectedChild = selectedChild,
            onChildSelected = {
                selectedChild = it
            }
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterDropdown(
                selectedValue = MonthYearUtils.monthNames[selectedDate.monthValue - 1],
                items = monthList,
                onItemSelected = { month ->
                    selectedDate = selectedDate.withMonth(
                        MonthYearUtils.monthNames.indexOf(month) + 1
                    )
                },
                modifier = Modifier.weight(1f)
            )
            FilterDropdown(
                selectedValue = selectedDate.year.toString(),
                items = yearList.map { it.toString() },
                onItemSelected = { year ->
                    selectedDate = MonthYearUtils.resolveYearMonth(
                        selected = selectedDate,
                        newYear = year.toInt()
                    )
                },
                modifier = Modifier.width(150.dp)
            )
        }
        Spacer(Modifier.height(12.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "November 2025",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 14.sp
                    )
                )

            }
            items(10) {

                HistoryCard(
                    onClick = {

                    }
                )
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    Innovillage6thTheme {
        HistoryScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}