package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun GenderField(
    selectedGender: String?,
    onGenderSelected: (String) -> Unit,
    enabled: Boolean = true
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Jenis Kelamin",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(end = 24.dp)
            ) {
                RadioButton(
                    selected = selectedGender == "Laki-Laki",
                    onClick = { onGenderSelected("Laki-Laki") },
                    enabled = enabled
                )
                Text(
                    text = "Laki-Laki", style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedGender == "Perempuan",
                    onClick = { onGenderSelected("Perempuan") },
                    enabled = enabled
                )
                Text(
                    text = "Perempuan", style = MaterialTheme.typography.titleMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}
