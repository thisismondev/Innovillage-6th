package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4

@Composable
fun CardKader(
    countChild: String,
    countMeasure: String
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 12.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
//            .padding(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Pink4
        )
    ) {
        Row(
            Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            StatItemCard(title = "Jumlah Anak", value = countChild)
            StatItemCard(title = "Pemeriksaan Bulan ini", value = countMeasure)
        }
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewCardKader() {
    Innovillage6thTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CardKader(
                countChild = "10",
                countMeasure = "5"
            )
        }
    }
}