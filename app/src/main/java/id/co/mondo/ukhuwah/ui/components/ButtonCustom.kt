package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun ButtonCustom(
    label: String, modifier: Modifier, onClick: () -> Unit
) {

    Button(
        onClick = {
            onClick()
        },
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            modifier = Modifier.padding(6.dp),
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewButtonCompose() {
    Innovillage6thTheme {
        Column(Modifier.fillMaxSize()) {
            ButtonCustom(
                label = "Selanjutnya", modifier = Modifier, onClick = {})
        }
    }
}