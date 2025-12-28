package id.co.mondo.ukhuwah.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.GenderField
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun RegisterScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {

    var gender by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }

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
            verticalArrangement = Arrangement.spacedBy(24.dp)
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
                        values = "",
                        label = "Nama Orang Tua",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Text
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = "",
                        label = "NIK",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Number
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
                        values = "",
                        label = "No. Handphone",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Number
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = "",
                        label = "Alamat",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Text
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = "",
                        label = "Email",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Email
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = "",
                        label = "Kata Sandi",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Password,
                        isPasswordField = true
                    )
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = "",
                        label = "Konfirmasi Kata Sandi",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Number
                    )

                }


            }
            item {
                ButtonCustom(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Daftar",
                    onClick = {

                    }
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
            contentPadding = PaddingValues()
        )
    }
}