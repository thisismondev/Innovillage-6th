package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@Composable
fun NavItemCard(
    modifier: Modifier = Modifier,
    title: String,
    color: Color,
    icon: Int,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.width(75.dp).clickable(
            onClick = {
                onClick()
            }
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(75.dp),
            colors = CardDefaults.cardColors(
                containerColor = color
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Icon(
                    painter = painterResource(icon),
                    contentDescription = null,
                    modifier = Modifier.size(50.dp),
                    tint = Color.Black,
                )
            }
        }
        Text(
            text = title,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 10.sp
            ),
            color = Color.Black,
        )
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewNavItemCard() {
    Innovillage6thTheme {
        NavItemCard(
            title = "Statistik",
            color = Pink4,
            icon = R.drawable.statistik,
            onClick = {

            }
        )
    }
}