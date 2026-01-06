package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.abu

@Composable
fun NutritionContent(
    energi: String,
    karbo: String,
    lemak: String,
    protein: String,
    zatBesi: String,
    zinc: String
){
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
    ) {
        Text(
            text = "Kandungan Per Porsi",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(Modifier.height(12.dp))
        NutritionItem(name = "Energi", value = "$energi kkal", isGray = true)
        NutritionItem(name = "Karbohidrat", value = "$karbo gr", isGray = false)
        NutritionItem(name = "Lemak", value = "$lemak gr", isGray = true)
        NutritionItem(name = "Protein", value = "$protein gr", isGray = false)
        NutritionItem(name = "Zat besi", value = "$zatBesi mg", isGray = true)
        NutritionItem(name = "Zinc", value = "$zinc mg", isGray = false)
    }
}

@Composable
fun NutritionItem(
    name: String,
    value: String,
    isGray: Boolean
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(if (isGray) abu else Color.Transparent)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal
            ),
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Normal
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNutritionContent(){
    Innovillage6thTheme {
        Column {
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