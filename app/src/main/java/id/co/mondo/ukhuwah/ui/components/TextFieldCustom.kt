package id.co.mondo.ukhuwah.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun TextFieldCustom(
    modifier: Modifier,
    values: String,
    label: String,
    onValueChange: (String) -> Unit,
    isPasswordField: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = values,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            unfocusedBorderColor = Color.LightGray,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary
        ),

        modifier = modifier,
        singleLine = singleLine,
        minLines = minLines,
        maxLines = maxLines,
        textStyle = MaterialTheme.typography.titleMedium,
        trailingIcon = {
            if (isPasswordField) {
                IconButton(
                    onClick = { isPasswordVisible = !isPasswordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            if (isPasswordVisible)
                                R.drawable.visibility
                            else
                                R.drawable.visibilityoff
                        ),
                        contentDescription = "Toggle Password Visibility",
                        tint = Color.LightGray
                    )
                }
            }
        },
        visualTransformation = if (isPasswordField && !isPasswordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        shape = RoundedCornerShape(14.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        enabled = enabled,
    )
}


@Preview(showBackground = true)
@Composable
fun TextFieldCustomPreview() {
    Innovillage6thTheme {
        Column(
            Modifier.fillMaxSize()
        ) {

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var address by remember { mutableStateOf("") }


            TextFieldCustom(
                modifier = Modifier,
                values = email,
                label = "Email",
                onValueChange = {email=it},
                isPasswordField = false,
                keyboardType = KeyboardType.Email,
            )
            TextFieldCustom(
                modifier = Modifier,
                values = password,
                label = "Email",
                onValueChange = {password=it},
                isPasswordField = true,
                keyboardType = KeyboardType.Password
            )
            TextFieldCustom(
                values = address,
                label = "Alamat",
                onValueChange = { address = it },
                singleLine = false,
                minLines = 3,
                maxLines = 5,
                keyboardType = KeyboardType.Text,
                modifier = Modifier.fillMaxWidth()
            )
            
        }
    }
}