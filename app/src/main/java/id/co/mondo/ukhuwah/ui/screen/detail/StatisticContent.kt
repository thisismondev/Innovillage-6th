package id.co.mondo.ukhuwah.ui.screen.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.data.model.AgeResult
import id.co.mondo.ukhuwah.data.model.ChildWithMeasure
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.DetailItemCard
import id.co.mondo.ukhuwah.ui.components.MpasiItemCard
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun StatisticContent(
    navController: NavController,
    child: ChildWithMeasure
) {


//    val fallback = MonthYearUtils.currentYearMonth()

//    val initialYear = child.year?.toIntOrNull() ?: fallback.year
//    val initialMonth = child.month?.toIntOrNull() ?: fallback.monthValue

//    var selectedDate by remember(child.children_id) {
//        mutableStateOf(
//            YearMonth.of(initialYear, initialMonth)
//        )
//    }

//    val yearList = MonthYearUtils.yearListFromBirth(child.birth)
//    val monthList = MonthYearUtils.monthListFromBirth(
//        birth = child.birth,
//        selectedYear = selectedDate.year
//    )
//
//    val isChildSelected = child.children_id != null

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Pink4),
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
//        item {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                FilterDropdown(
//                    selectedValue = MonthYearUtils.monthNames[selectedDate.monthValue - 1],
//                    items = monthList,
//                    onItemSelected = { month ->
//                        val newMonth = MonthYearUtils.monthNames.indexOf(month) + 1
//                        selectedDate = selectedDate.withMonth(newMonth)
//
//                        onMonthYearChanged(
//                            selectedDate.year.toString(),
//                            newMonth.toString().padStart(2,'0')
//                        )
//                    },
//                    enabled = isChildSelected,
//                    modifier = Modifier.weight(1F)
//                )
//                FilterDropdown(
//                    selectedValue = selectedDate.year.toString(),
//                    items = yearList.map { it.toString() },
//                    onItemSelected = { year ->
//                        selectedDate = MonthYearUtils.resolveYearMonth(
//                            selected = selectedDate,
//                            newYear = year.toInt()
//                        )
//
//                        onMonthYearChanged(
//                            year,
//                            selectedDate.monthValue.toString().padStart(2,'0')
//                        )
//                    },
//                    enabled = isChildSelected,
//                    modifier = Modifier.width(150.dp)
//                )
//            }
//        }

        item {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailItemCard(
                    painter = painterResource(R.drawable.babyface),
                    title = "Usia anak",
                    value = "${child.ageResult?.years ?: 0} Tahun " +
                            "${child.ageResult?.months ?: 0} Bulan " +
                            "${child.ageResult?.days ?: 0} Hari",
                    sizeFont = 18,
                    modifier = Modifier.size(height = 130.dp, width = 250.dp)
                )
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    DetailItemCard(
                        painter = painterResource(R.drawable.trust),
                        title = "Berat (kg)",
                        value = child.weightKg.toString(),
                    )
                    DetailItemCard(
                        painter = painterResource(R.drawable.length),
                        title = "Tinggi (cm)",
                        value = child.heightCm.toString(),
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    DetailItemCard(
                        painter = painterResource(R.drawable.raisinghand),
                        title = "LiLA (cm)",
                        value = child.armCm.toString(),
                    )
                    DetailItemCard(
                        painter = painterResource(R.drawable.headbrain),
                        title = "LK (cm)",
                        value = child.headCm.toString(),
                    )
                }
            }
        }
//        item {
//            AppTopBar(
//                title = "Laporan Pertumbuhan",
//                showBack = false,
//                modifier = Modifier.background(Color.White)
//            )
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(55.dp)
//                    .background(color = MaterialTheme.colorScheme.primary)
//                    .padding(horizontal = 20.dp),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Box(
//                    modifier = Modifier
//                        .size(32.dp)
//                        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
//                        .clickable(
//                            onClick = {
//
//                            }
//                        ),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.KeyboardArrowLeft,
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp),
//                        tint = MaterialTheme.colorScheme.primary,
//                    )
//                }
//
//                Text(
//                    text = "Berat Badan / Tinggi Badan",
//                    style = MaterialTheme.typography.titleLarge.copy(
//                        fontSize = 16.sp
//                    ),
//                    color = Color.White,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.weight(1F)
//                )
//                Box(
//                    modifier = Modifier
//                        .size(32.dp)
//                        .background(color = Color.White, shape = RoundedCornerShape(12.dp))
//                        .clickable(
//                            onClick = {
//
//                            }
//                        ),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Icon(
//                        imageVector = Icons.Default.KeyboardArrowRight,
//                        contentDescription = null,
//                        modifier = Modifier.size(24.dp),
//                        tint = MaterialTheme.colorScheme.primary,
//                    )
//                }
//            }
//        }
//        item {
//            BasicLineChart(
//                modifier = Modifier
//                    .padding(horizontal = 20.dp)
//            )
//        }
//        item {
//            OutlinedCard(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 20.dp),
//                shape = RoundedCornerShape(20.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White
//                )
//            ) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(horizontal = 20.dp, vertical = 12.dp),
//                    verticalArrangement = Arrangement.spacedBy(12.dp)
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp)
//                            .padding(horizontal = 32.dp)
//                            .background(
//                                color = MaterialTheme.colorScheme.secondary,
//                                shape = RoundedCornerShape(12.dp)
//                            ),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ) {
//                        Text(
//                            text = "Obesitas",
//                            style = MaterialTheme.typography.bodyMedium,
//                            color = Color.White
//                        )
//                    }
//                    Text(
//                        text = "Lompo Battang",
//                        style = MaterialTheme.typography.bodySmall,
//                        color = Color.Black,
//                    )
//                }
//            }
//        }
        item {

            AppTopBar(
                title = "Rekomendasi Makanan",
                showBack = false,
                modifier = Modifier.background(Color.White)
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Lihat Semua",
                    style = MaterialTheme.typography.titleSmall.copy(
                        textDecoration = TextDecoration.Underline
                    ),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate("health")
                    }
                )
            }
        }
        items(10) {
            MpasiItemCard(
                navController = navController,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 20.dp),
                fontTitle = MaterialTheme.typography.titleMedium.copy(
                    lineHeight = 16.sp,
                    fontWeight = FontWeight.Bold
                ),
                fontBody = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                )
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewStaticContent() {
    Innovillage6thTheme {
        StatisticContent(
            navController = rememberNavController(), child = ChildWithMeasure(
                ageResult = AgeResult(
                    years = 0,
                    months = 0,
                    days = 0
                )
            )
        )
    }
}