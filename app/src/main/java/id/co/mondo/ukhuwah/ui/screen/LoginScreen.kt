package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen(viewModel: AuthViewModel){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .padding(20.dp)
    ) {
        Text(
            text = "Masuk Akun",
            style = MaterialTheme.typography.titleLarge.copy(
                fontSize = 32.sp,
                lineHeight = 48.sp
            ),
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Untuk menggunakan akun Anda,\nanda harus login terlebih dahulu",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Light,
                lineHeight = 24.sp
            ),
        )
        Spacer(Modifier.height(24.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            TextFieldCustom(
                modifier = Modifier.fillMaxWidth(),
                values = viewModel.email,
                label = "Email",
                onValueChange = {
                    viewModel.email = it
                },
                keyboardType = KeyboardType.Email
            )
            TextFieldCustom(
                modifier = Modifier.fillMaxWidth(),
                values = viewModel.password,
                label = "Password",
                onValueChange = {
                    viewModel.password = it
                },
                isPasswordField = true,
                keyboardType = KeyboardType.Password
            )
            TextFieldCustom(
                modifier = Modifier.fillMaxWidth(),
                values = viewModel.confirmPassword,
                label = "Confirm Password",
                onValueChange = {
                    viewModel.confirmPassword = it
                },
                isPasswordField = true,
                keyboardType = KeyboardType.Password
            )
        }
        Spacer(Modifier.height(32.dp))
        ButtonCustom(
            modifier = Modifier.fillMaxWidth(),
            label = "Login",
            onCLick = {

            }
        )
    }

}



@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen(){
    Innovillage6thTheme {
        LoginScreen(viewModel = AuthViewModel())
    }
}
