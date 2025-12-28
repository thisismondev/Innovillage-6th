package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.FilterTabs
import id.co.mondo.ukhuwah.ui.components.HistoryCard
import id.co.mondo.ukhuwah.ui.components.SelectChildren
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun HistoryScreen() {

    var selectedTab by remember { mutableStateOf("Semua") }
    var selectedChild by remember { mutableStateOf("Semua") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
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
        Spacer(Modifier.height(24.dp))
        FilterTabs(
            selectedTab = selectedTab,
            onTabSelected = {
                selectedTab = it
            }
        )
        Spacer(Modifier.height(24.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "November 2025",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp
                )
            )
            HistoryCard(
                onClick = {

                }
            )
            HistoryCard(
                onClick = {

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHistoryScreen() {
    Innovillage6thTheme {
        HistoryScreen()
    }
}