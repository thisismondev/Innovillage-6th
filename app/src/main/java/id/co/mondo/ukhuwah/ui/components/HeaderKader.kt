package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun HeaderKader(
    onClickNotification: () -> Unit = {},
    onClickLogout: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Posyandu Bontoulu",
            style = MaterialTheme.typography.titleMedium.copy(
                fontSize = 18.sp
            ),
            color = Color.White
        )
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .clickable(
                        onClick = {
                            onClickNotification()
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.notification),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(12.dp))
                    .clickable(
                        onClick = {
                            onClickLogout()
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.logout),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHeaderKader() {
    Innovillage6thTheme {
        HeaderKader()
    }
}