package id.co.mondo.ukhuwah.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.menuItems
import id.co.mondo.ukhuwah.data.utils.MonthYearUtils
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.CardKader
import id.co.mondo.ukhuwah.ui.components.FilterDropdown
import id.co.mondo.ukhuwah.ui.components.HeaderKader
import id.co.mondo.ukhuwah.ui.components.NavItemCard
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val currentDate = MonthYearUtils.currentYearMonth()
    var selectedDate by remember { mutableStateOf(currentDate) }

    val yearList = MonthYearUtils.yearList(2024)
    val monthList = MonthYearUtils.monthList(selectedDate.year)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderKader(
                onClickNotification = {
                    navController.navigate("notification")

                },
                onClickLogout = {
                    navController.navigate("login") {
                        popUpTo(navController.graph.startDestinationId) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = -20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)

        ) {
            CardKader()
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(menuItems) { item ->
                    NavItemCard(
                        title = item.title,
                        color = item.color,
                        icon = item.icon,
                        onClick = {
                            if (item.title == "Export Data Anak") {
                                showBottomSheet = true
                            } else {
                                navController.navigate(item.routes)
                            }

                        }
                    )

                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Export Data Anak",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        Text(
                            text = "Silahkan Pilih Bulan dan Tahun yang akan di Export",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
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
                                modifier = Modifier.weight(1F)
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
                        ButtonCustom(
                            onClick = {
                                scope.launch { sheetState.hide() }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            },
                            label = "Export",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }


    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Innovillage6thTheme {
        HomeScreen(navController = rememberNavController())
    }
}