package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@Composable
fun MpasiItemCard(
    navController: NavController,
    modifier: Modifier = Modifier,
    fontBody: TextStyle,
    fontTitle: TextStyle
) {
    Card(
        modifier = modifier
            .height(125.dp)
            .clickable(
                onClick = {
                    navController.navigate("detail-mpasi")
                }
            ),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .weight(1F),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Sop Bola Udang",
                    style = fontTitle
//                        MaterialTheme.typography.titleMedium.copy(
//                        fontSize = 12.sp,
//                        lineHeight = 16.sp,
//                        fontWeight = FontWeight.Bold
//                    )
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
                        style = fontBody,
//                            MaterialTheme.typography.bodyMedium.copy(
//                            fontSize = 10.sp
//                        ),
                        color = Color.Gray
                    )
                }
                Spacer(Modifier.weight(1F))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.eye),
                            contentDescription = null,
                            tint = Pink4,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "100",
                            style = fontBody
//                                MaterialTheme.typography.bodyMedium.copy(
//                                fontSize = 12.sp
//                            )
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.favorite),
                            contentDescription = null,
                            tint = Pink4,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "100",
                            style = fontBody
//                                MaterialTheme.typography.bodyMedium.copy(
//                                fontSize = 12.sp
//                            )
                        )
                    }
                    Icon(
                        painter = painterResource(R.drawable.bookmark),
                        contentDescription = null,
                        tint = Pink4,
                        modifier = Modifier.size(16.dp)
                    )
                }

            }
            Image(
                painter = painterResource(R.drawable.bubur),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp)
                    .shadow(8.dp, RoundedCornerShape(percent = 25))
            )


        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewPmasiItemCard() {
    Innovillage6thTheme {
        Column(Modifier.fillMaxSize()) {
            MpasiItemCard(
                navController = rememberNavController(),
                modifier = Modifier
                    .size(width = 250.dp, height = 200.dp),
                fontBody = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp
                ),
                fontTitle = MaterialTheme.typography.titleMedium
            )
        }
    }
}
