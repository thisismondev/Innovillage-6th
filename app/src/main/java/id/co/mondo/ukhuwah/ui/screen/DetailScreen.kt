package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun DetailScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Detail",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 16.sp
                )
            )
        }


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Innovillage6thTheme {
        DetailScreen()
    }
}