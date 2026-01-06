package id.co.mondo.ukhuwah.ui.screen.health

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.DescriptionContent
import id.co.mondo.ukhuwah.ui.components.NutritionContent
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@Composable
fun DetailMpasiScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        AppTopBar(
            title = "Resep Detail",
            showBack = true,
            onBackClick = {
                navController.popBackStack()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.bubur),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp)
                        .height(200.dp)
                        .shadow(8.dp, RoundedCornerShape(percent = 25))
                )
            }
            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        Modifier
                            .weight(1F),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Sop Bola Udang",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.babyface),
                                contentDescription = null,
                                tint = Pink4,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "12 Bulan ke atas",
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp
                                ),
                                color = Color.Gray
                            )
                        }
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(24.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.eye),
                                contentDescription = null,
                                tint = Pink4,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "100", style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp
                                )
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.favorite),
                                contentDescription = null,
                                tint = Pink4,
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = "100", style = MaterialTheme.typography.bodyMedium.copy(
                                    fontSize = 12.sp
                                )
                            )
                        }
                        Icon(
                            painter = painterResource(R.drawable.bookmark),
                            contentDescription = null,
                            tint = Pink4,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            item {
                DescriptionContent(
                    title = "Bahan",
                    items = listOf(
                        "Deskripsi \n 1",
                        "Deskripsi 2",
                        "Deskripsi 3"
                    )
                )
            }
            item {
                DescriptionContent(
                    title = "Cara Membuat",
                    items = listOf(
                        "Deskripsi \n 1",
                        "Deskripsi 2",
                        "Deskripsi 3"
                    )
                )
            }
            item {
                NutritionContent(
                    energi = "100",
                    karbo = "100",
                    lemak = "100",
                    protein = "100",
                    zatBesi = "100",
                    zinc = "100"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailMpasiScreen() {
    Innovillage6thTheme {
        DetailMpasiScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}
