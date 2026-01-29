package id.co.mondo.ukhuwah.ui.screen.auth

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.InsertUser
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.GenderField
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    contentPadding: PaddingValues
) {
    val context = LocalContext.current

    val registerState by authViewModel.registerState.collectAsState()

    var email: String by rememberSaveable { mutableStateOf("") }
    var password: String by rememberSaveable { mutableStateOf("") }
    var confirmPassword: String by rememberSaveable { mutableStateOf("") }
    var name: String by rememberSaveable { mutableStateOf("") }
    var nik: String by rememberSaveable { mutableStateOf("") }
    var gender: String by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var phone: String by rememberSaveable { mutableStateOf("") }
    var address: String by rememberSaveable { mutableStateOf("") }

    val showEmailNotInvalid = email.isNotEmpty() &&
            !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val showPasswordError = password.isNotEmpty() && password.length < 6
    val showConfirmPasswordError = confirmPassword.isNotEmpty() && confirmPassword != password

    val isFormValid = !showEmailNotInvalid &&
            !showPasswordError &&
            !showConfirmPasswordError &&
            name.isNotEmpty() &&
            nik.isNotEmpty() &&
            gender.isNotEmpty() &&
            birthDate.isNotEmpty() &&
            phone.isNotEmpty() &&
            address.isNotEmpty()



    LaunchedEffect(registerState) {
        when (registerState) {
            is UiState.Success -> {
                Toast.makeText(
                    context,
                    "Berhasil Membuat Akun",
                    Toast.LENGTH_SHORT
                ).show()

                authViewModel.resetRegisterState()

                navController.navigate("parent") {
                    popUpTo("register") { inclusive = true }
                }
            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (registerState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()

                authViewModel.resetRegisterState()
            }

            else -> Unit
        }
    }
    if (registerState is UiState.Loading) {
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




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
    ) {
        AppTopBar(
            title = "Buat Akun",
            showBack = true,
            onBackClick = {
                navController.popBackStack()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Text(
                    text = "Buat akun untuk Orang Tua,\nsilahkan lengkapi  proses daftar",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Light,
                        lineHeight = 24.sp
                    ),
                )
            }
            item {
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = name,
                        label = "Nama Orang Tua",
                        onValueChange = {
                            name = it
                        },
                        keyboardType = KeyboardType.Text,
                        singleLine = true,
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = nik,
                        label = "NIK",
                        onValueChange = {
                            nik = it
                        },
                        keyboardType = KeyboardType.Number,
                        singleLine = true,
                    )
                    GenderField(
                        selectedGender = gender,
                        onGenderSelected = {
                            gender = it
                        }
                    )
                    TextFieldDateCustom(
                        modifier = Modifier.fillMaxWidth(),
                        value = birthDate,
                        label = "Tanggal Lahir",
                        onDateSelected = { birthDate = it }
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = phone,
                        label = "No. Handphone",
                        onValueChange = {
                            phone = it
                        },
                        keyboardType = KeyboardType.Number,
                        singleLine = true,
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = address,
                        label = "Alamat",
                        onValueChange = {
                            address = it
                        },
                        keyboardType = KeyboardType.Text,
                        singleLine = true,
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = email,
                        label = "Email",
                        onValueChange = {
                            email = it
                        },
                        keyboardType = KeyboardType.Email,
                        singleLine = true,
                        isError = showEmailNotInvalid,
                        supportingText = if (showEmailNotInvalid) {
                            {
                                Text(
                                    text = "Email tidak valid",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontSize = 10.sp
                                    )
                                )
                            }

                        } else null
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = password,
                        label = "Kata Sandi",
                        onValueChange = {
                            password = it
                        },
                        keyboardType = KeyboardType.Password,
                        isPasswordField = true,
                        isError = showPasswordError,
                        singleLine = true,
                        supportingText = if (showPasswordError) {
                            {
                                Text(
                                    text = "Password minimal 6 karakter",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        } else null

                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = confirmPassword,
                        label = "Konfirmasi Kata Sandi",
                        onValueChange = {
                            confirmPassword = it
                        },
                        keyboardType = KeyboardType.Password,
                        singleLine = true,
                        isPasswordField = true,
                        isError = showConfirmPasswordError,
                        supportingText = if (showConfirmPasswordError) {
                            {
                                Text(
                                    text = "Password tidak sama",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        fontSize = 10.sp
                                    )
                                )
                            }
                        } else null
                    )
                }
            }
            item {
                ButtonCustom(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Daftar",
                    onClick = {
                        authViewModel.createAccount(
                            email = email,
                            password = password,
                            confirmPassword = confirmPassword,
                            user = InsertUser(
                                name = name,
                                nik = nik,
                                gender = gender,
                                birth = birthDate,
                                phone = phone,
                                address = address,
                            )
                        )
                    },
                    enabled = isFormValid
                )
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewRegisterScreen() {
    Innovillage6thTheme {
        RegisterScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues(),
            authViewModel = viewModel()
        )
    }
}