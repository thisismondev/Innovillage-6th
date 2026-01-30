package id.co.mondo.ukhuwah.ui.screen.auth

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

@Composable
fun LoginScreen( viewModel: AuthViewModel) {

    val context = LocalContext.current

    var email: String by remember { mutableStateOf("") }
    var password: String by remember { mutableStateOf("") }

    val loginState by viewModel.loginState.collectAsState()

    when (loginState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
        is UiState.Error -> {
                Toast.makeText(
                    context,
                    (loginState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        else -> Unit
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
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
                values = email,
                label = "Email",
                onValueChange = {
                   email = it
                },
                keyboardType = KeyboardType.Email,
            )
            TextFieldCustom(
                modifier = Modifier.fillMaxWidth(),
                values = password,
                label = "Kata Sandi",
                onValueChange = {
                    password = it
                },
                isPasswordField = true,
                keyboardType = KeyboardType.Password,
            )
        }
        Spacer(Modifier.height(32.dp))
        ButtonCustom(
            modifier = Modifier.fillMaxWidth(),
            label = "Masuk",
            onClick = {
                Log.d("LOGIN", "Button clicked")
                viewModel.login(
                    email = email,
                    password = password
                )
            }
        )
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    Innovillage6thTheme {
        LoginScreen( viewModel = viewModel())
    }
}
