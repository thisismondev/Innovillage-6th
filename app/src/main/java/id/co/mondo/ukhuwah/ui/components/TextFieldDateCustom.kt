package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.data.utils.formatDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldDateCustom(
    modifier: Modifier = Modifier,
    value: String,
    label: String,
    onDateSelected: (String) -> Unit,
    enabled: Boolean = true
) {
    val datePickerState = rememberDatePickerState()
    var showDialog by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = {},
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        },
        modifier = modifier,
        readOnly = true,
        enabled = enabled,
        singleLine = true,
        textStyle = MaterialTheme.typography.titleMedium,
        shape = RoundedCornerShape(14.dp),

        trailingIcon = {
            IconButton(
                onClick = { showDialog = true },
                enabled = enabled
            ) {
                Icon(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "Pick date",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(24.dp)
                )
            }
        },

        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        ),

        interactionSource = remember { MutableInteractionSource() }
            .also { source ->
                LaunchedEffect(source) {
                    source.interactions.collect {
                        if (it is PressInteraction.Release) {
                            showDialog = true
                        }
                    }
                }
            }
    )

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(formatDate(it))
                        }
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Batal")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}
