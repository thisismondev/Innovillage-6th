package id.co.mondo.ukhuwah.ui.screen

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.menuItemsParent
import id.co.mondo.ukhuwah.data.utils.getAvailableMonthsByYear
import id.co.mondo.ukhuwah.ui.components.ArticleItemCard
import id.co.mondo.ukhuwah.ui.components.CardChild
import id.co.mondo.ukhuwah.ui.components.HeaderParent
import id.co.mondo.ukhuwah.ui.components.MpasiItemCard
import id.co.mondo.ukhuwah.ui.components.NavItemCard
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeParentScreen(
    navController: NavController,
    contentPadding: PaddingValues,
) {

    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }

    val selectedYear = "2026"
    val monthList = remember(selectedYear) {
        getAvailableMonthsByYear(selectedYear)
    }
    var selectedMonth by remember { mutableStateOf(monthList.first()) }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Konfirmasi",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            text = {
                Text(text = "Yakinki mau Keluar?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
//                        authViewModel.logout()
                    }
                ) {
                    Text(
                        text = "Keluar",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text(
                        text = "Kembali",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            }
        )
    }


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentPadding = contentPadding,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                    )
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(
                        space = 12.dp,
                        alignment = Alignment.CenterVertically
                    )
                ) {
                    HeaderParent(
                        onClickNotification = {
                            navController.navigate("notification")
                        },
                        onClickProfile = {
                            showDialog = true
                        }
                    )
                    Text(
                        text = "Profile Anak",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 18.sp
                        ),
                        color = Color.White
                    )
                }
            }
        }
        item {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .offset(y = -40.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(10) {
                        CardChild()
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 24.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    menuItemsParent.forEach { item ->
                        NavItemCard(
                            title = item.title,
                            color = item.color,
                            icon = item.icon,
                            onClick = {

                            }
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Rekomendasi untuk Anda",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 12.sp,
                        ),
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(4) {
                            MpasiItemCard(
                                navController = navController,
                                modifier = Modifier
                                    .width(250.dp),
                                fontTitle = MaterialTheme.typography.titleMedium.copy(
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                fontBody = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 10.sp
                                )
                            )
                        }
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Rekomendasi untuk Anda",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 12.sp,
                        ),
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(4) {
                            ArticleItemCard(
                                modifier = Modifier
                                    .size(
                                        width = 225.dp,
                                        height = 200.dp
                                    )
                            )
                        }
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomeParentScreen() {
    Innovillage6thTheme {
        HomeParentScreen(navController = rememberNavController(), contentPadding = PaddingValues())
    }
}