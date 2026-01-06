package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun DescriptionContent(
    title: String,
    items: List<String>
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items.forEach { item ->
                BulletText(text = item)
            }
        }
    }


}

@Composable
fun BulletText(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("• ", style = MaterialTheme.typography.bodyMedium)
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 22.sp
            ),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDescContent() {
    Innovillage6thTheme {
        Column(
            Modifier.fillMaxSize()
        ) {
            DescriptionContent(
                title = "Deskripsi",
                items = listOf(
                    "Deskripsi \n 1",
                    "Deskripsi 2",
                    "Deskripsi 3"
                )

            )
        }
    }
}