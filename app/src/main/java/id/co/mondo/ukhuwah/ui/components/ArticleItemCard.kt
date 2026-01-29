package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@Composable
fun ArticleItemCard(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable(
                onClick = {
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
        Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.SpaceBetween) {
            Image(
                painter = painterResource(R.drawable.bubur),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
            )
            Column(
                modifier = Modifier.fillMaxSize().padding(horizontal = 12.dp).padding(bottom = 8.dp),
                verticalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.Bottom)
            ) {
                Text(
                    text = "Kebijakan stunting yang tidak membahas anak yang ‘Sedang’ Stungting",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 10.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.like),
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
                        Spacer(Modifier.width(14.dp))
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

                }
            }


        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewArticleItemCard() {
    Innovillage6thTheme {
        Column(Modifier.fillMaxSize()) {
            ArticleItemCard(
                modifier = Modifier
                    .size(width = 250.dp, height = 200.dp )
            )
        }
    }
}