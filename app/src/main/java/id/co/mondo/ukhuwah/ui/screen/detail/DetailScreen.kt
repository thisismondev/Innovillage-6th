package id.co.mondo.ukhuwah.ui.screen.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.SelectChildren
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {

    val tabs = listOf("Statistik", "Perjalanan")
    var selectedTabIndex by remember { mutableStateOf(0) }


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
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = selectedTabIndex == index

                Tab(
                    selected = selected,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier
                        .background(
                            color = if (selected)
                                Pink4
                            else
                                Color.White,
                        ),
                    text = {
                        Text(
                            text = title,
                            color = if (selected)
                                Color.Black
                            else
                                Color.Gray,
                            style = if (selected) {
                                MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                MaterialTheme.typography.titleMedium
                            }
                        )
                    }
                )
            }
        }
        when (selectedTabIndex) {
            0 -> StatisticContent(navController)
            1 -> GrowthContent()
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Innovillage6thTheme {
        DetailScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}