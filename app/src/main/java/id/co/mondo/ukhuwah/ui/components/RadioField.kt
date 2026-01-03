package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioField(
    selectedRadio: String?,
    onRadioSelected: (String) -> Unit,
    enabled: Boolean = true
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 24.dp)
        ) {
            RadioButton(
                selected = selectedRadio == "Manual",
                onClick = { onRadioSelected("Manual") },
                enabled = enabled
            )
            Text(
                text = "Manual", style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = selectedRadio == "Otomatis",
                onClick = { onRadioSelected("Otomatis") },
                enabled = enabled
            )
            Text(
                text = "Otomatis", style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        }
    }

}