package id.co.mondo.ukhuwah.ui.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.MeasureWithChild
import id.co.mondo.ukhuwah.data.model.Measurements
import id.co.mondo.ukhuwah.data.utils.MonthYearUtils
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.FilterDropdown
import id.co.mondo.ukhuwah.ui.components.HistoryCard
import id.co.mondo.ukhuwah.ui.components.SelectChildren
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HistoryScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {
    val context = LocalContext.current

    val userViewModel: UserViewModel = viewModel()
    val measureWithChildState by userViewModel.measureChildState.collectAsState()
    val childState by userViewModel.childState.collectAsState()


    val currentDate = MonthYearUtils.currentYearMonth()
    var selectedDate by remember { mutableStateOf(currentDate) }

    val yearList = MonthYearUtils.yearList(2024)
    val monthList = MonthYearUtils.monthList(selectedDate.year)


    var selectedChild by remember { mutableStateOf("Semua") }


    LaunchedEffect(Unit) {
        userViewModel.getAllMeasureChild()
        userViewModel.getAllChild()
    }

    val childrenForSelect = when (childState) {
        is UiState.Success -> (childState as UiState.Success).data
        else -> emptyList()
    }


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
            },
            children = childrenForSelect
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

        when (measureWithChildState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            is UiState.Success -> {
                val children = (measureWithChildState as UiState.Success<List<MeasureWithChild>>).data
                Log.d("HistoryScreen", "Children: $children")

                val filteredChildren = if (selectedChild == "Semua") {
                    children
                } else {
                    children.filter {
                        it.name == selectedChild
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
//                    item {
//                        Text(
//                            text = "November 2025",
//                            style = MaterialTheme.typography.titleMedium.copy(
//                                fontSize = 14.sp
//                            )
//                        )
//
//                    }
                    items(filteredChildren) { measure ->
                        HistoryCard(
                            name = measure.name,
                            measure = Measurements(
                                measured_at = measure.measurements?.measured_at,
                                headCm = measure.measurements?.headCm,
                                weightKg = measure.measurements?.weightKg,
                                armCm = measure.measurements?.armCm,
                                heightCm = measure.measurements?.heightCm
                            ),
                            onClick = { }
                        )
                    }
                }
            }
            is UiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tidak ada data",
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (measureWithChildState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
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