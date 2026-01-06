package id.co.mondo.ukhuwah.ui.screen.health

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import id.co.mondo.ukhuwah.ui.components.ArticleItemCard
import id.co.mondo.ukhuwah.ui.components.MpasiItemCard
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun HealthScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {

    val tabs = listOf("Artikel", "MPASI")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        AppTopBar(
            title = "Informasi Kesehatan",
            showBack = true,
            onBackClick = {
                navController.popBackStack()
            },
            showBookmark = true,
            onBookmarkClick = {

            }
        )
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = selectedTabIndex == index

                Tab(
                    selected = selected,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier
                        .background(
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
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(10) {
                when(selectedTabIndex){
                    0 -> ArticleItemCard()
                    1 -> MpasiItemCard(navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Innovillage6thTheme {
        HealthScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}