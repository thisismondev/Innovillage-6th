package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.ui.theme.abu

@Composable
fun TitleNotif(
    title: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = abu)
            .padding(horizontal = 20.dp, vertical = 6.dp)
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewTitleNotif() {
    TitleNotif(
        "Today"
    )
}