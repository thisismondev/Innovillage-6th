package id.co.mondo.ukhuwah.ui.screen.family

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.GenderField
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun AddChildrenScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {

    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }


    Column(
        Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        AppTopBar(
            title = "Tambah Anak",
            showBack = true,
            onBackClick = {
                navController.popBackStack()
            },
            showSearch = true,
            onSearchClick = {

            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
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
            }
            item {
                Text(
                    text = "Informasi Anak",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                TextFieldCustom(
                    modifier = Modifier.fillMaxWidth(),
                    values = "",
                    label = "Nama",
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
            }
            item {
                Text(
                    text = "Data kelahiran",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TextFieldCustom(
                            modifier = Modifier.weight(1f),
                            values = "",
                            label = "TB",
                            onValueChange = {},
                            keyboardType = KeyboardType.Decimal
                        )
                        TextFieldCustom(
                            modifier = Modifier.weight(1f),
                            values = "",
                            label = "BB",
                            onValueChange = {},
                            keyboardType = KeyboardType.Decimal
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TextFieldCustom(
                            modifier = Modifier.weight(1f),
                            values = "",
                            label = "LK",
                            onValueChange = {},
                            keyboardType = KeyboardType.Decimal
                        )
                        TextFieldCustom(
                            modifier = Modifier.weight(1f),
                            values = "",
                            label = "LiLA",
                            onValueChange = {},
                            keyboardType = KeyboardType.Decimal
                        )
                    }
                }

            }
            item {
                ButtonCustom(
                    modifier = Modifier.fillMaxWidth(),
                    label = "Simpan",
                    onClick = {

                    }
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun PreviewAddChildrenScreen() {
    Innovillage6thTheme {
        AddChildrenScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}