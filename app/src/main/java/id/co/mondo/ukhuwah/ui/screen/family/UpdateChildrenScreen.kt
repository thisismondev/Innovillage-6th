package id.co.mondo.ukhuwah.ui.screen.family

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.ChildrenCard
import id.co.mondo.ukhuwah.ui.components.RadioField
import id.co.mondo.ukhuwah.ui.components.StatusIndicator
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme

@Composable
fun UpdateChildrenScreen(
    navController: NavController,
    paddingvalues: PaddingValues
) {

    var birthDate by remember { mutableStateOf("") }
    var metodeInput by remember { mutableStateOf("Otomatis") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingvalues)
    ) {
        AppTopBar(
            title = "Update Pertumbuhan Anak",
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
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Informasi Anak",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    ChildrenCard(
                        name = "Arnawati",
                        onClick = {
                        },
                        showIcon = false,
                        select = true

                        )
                    ChildrenCard(
                        name = "La Ode",
                        onClick = {
                        },
                        showIcon = false,
                        select = false
                    )
                }
            }
            item {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Metode Input",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    TextFieldDateCustom(
                        modifier = Modifier.fillMaxWidth(),
                        value = birthDate,
                        label = "Tanggal Pengukuran",
                        onDateSelected = { birthDate = it }
                    )
                    RadioField(
                        selectedRadio = metodeInput,
                        onRadioSelected = {
                            metodeInput = it
                        },
                    )
                    if (metodeInput == "Manual") {
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
                    } else {
                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp),
                        ) {
                            Column(
                                modifier = Modifier.padding(20.dp),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                ButtonCustom(
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Mulai Pengukuran",
                                    onClick = {

                                    }
                                )
                                Row(
                                    Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    StatusIndicator(
                                        painter = painterResource(R.drawable.done),
                                        color = Color.Green,
                                        tintIcon = Color.White
                                    )
                                    Text(
                                        text = "Status: Terhubung",
                                        style = MaterialTheme.typography.bodyMedium
                                    )
                                }
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
                            }
                        }
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
fun PreviewUpdateChildrenScreen() {
    Innovillage6thTheme {

        UpdateChildrenScreen(
            navController = rememberNavController(),
            paddingvalues = PaddingValues()
        )
    }
}