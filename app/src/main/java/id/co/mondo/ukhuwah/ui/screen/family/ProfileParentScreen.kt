package id.co.mondo.ukhuwah.ui.screen.family

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ChildrenCard
import id.co.mondo.ukhuwah.ui.components.GenderField
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun ProfileParentScreen(
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
            title = "Profile Keluarga",
            showBack = true,
            onBackClick = {
                navController.popBackStack()
            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Informasi Orang Tua",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
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
                    TextFieldCustom(
                        modifier = Modifier.fillMaxWidth(),
                        values = "",
                        label = "Email",
                        onValueChange = {

                        },
                        keyboardType = KeyboardType.Email
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
                }
            }
            item {
                Column(
                    Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Anak - Anak",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                        Text(
                            text = "Tambah",
                            style = MaterialTheme.typography.titleSmall.copy(
                                textDecoration = TextDecoration.Underline
                            ),
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.clickable(
                                onClick = {
                                    navController.navigate("add-children")
                                }
                            )
                        )
                    }
                    ChildrenCard(
                        name = "Arnawati",
                        onClick = {
                            navController.navigate("profile-children")
                        }
                    )
                    ChildrenCard(
                        name = "La ode",
                        onClick = {

                        }
                    )
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewProfileFamilyScreen() {
    Innovillage6thTheme {
        ProfileParentScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}