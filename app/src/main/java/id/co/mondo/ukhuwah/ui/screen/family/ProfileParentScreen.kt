package id.co.mondo.ukhuwah.ui.screen.family

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.User
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ChildrenCard
import id.co.mondo.ukhuwah.ui.components.GenderField
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.UserViewModel

@Composable
fun ProfileParentScreen(
    navController: NavController,
    idUser: String,
    contentPadding: PaddingValues
) {

    val context = LocalContext.current

    val userViewModel: UserViewModel = viewModel()
    val state by userViewModel.userId.collectAsState()

    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nik by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }


    var isEditMode by remember { mutableStateOf(false) }
    var enabled by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(idUser) {
        userViewModel.getUserById(idUser)
    }

    LaunchedEffect(state) {
        if (state is UiState.Success) {
            val users = (state as UiState.Success<User>).data

            name = users.name.orEmpty()
            nik = users.nik.orEmpty()
            birthDate = users.birth.orEmpty()
            gender = users.gender.orEmpty()
            phone = users.phone.orEmpty()
            address = users.address.orEmpty()
            email = users.email.orEmpty()
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Update",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            text = {
                Text(text = "Yakin ki mau Simpan perubahan?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        userViewModel.updateUser(
                            User(
                                id_users = idUser,
                                name = name,
                                nik = nik,
                                gender = gender,
                                birth = birthDate,
                                phone = phone,
                                address = address,
                            )
                        )
                        enabled = false
                        isEditMode = false
                        showDialog = false
                    }
                ) {
                    Text(
                        text = "Simpan",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        enabled = false
                        isEditMode = false
                        showDialog = false
                    }
                ) {
                    Text(
                        text = "Kembali",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            }
        )
    }

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
            },
            showEdit = true,
            isEditMode = isEditMode,
            onSaveClick = {
                if (isEditMode) {
                    showDialog = true
                } else {
                    enabled = true
                    isEditMode = true
                }
            }
        )
        when (state) {

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
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
                                values = name,
                                label = "Nama Orang Tua",
                                onValueChange = {
                                    name = it

                                },
                                keyboardType = KeyboardType.Text,
                                enabled = enabled
                            )
                            TextFieldCustom(
                                modifier = Modifier.fillMaxWidth(),
                                values = nik,
                                label = "NIK",
                                onValueChange = {
                                    nik = it
                                },
                                keyboardType = KeyboardType.Number,
                                enabled = enabled
                            )
                            TextFieldCustom(
                                modifier = Modifier.fillMaxWidth(),
                                values = email,
                                label = "Email",
                                onValueChange = {
                                    email = it
                                },
                                keyboardType = KeyboardType.Email,
                                enabled = false
                            )
                            GenderField(
                                selectedGender = gender,
                                onGenderSelected = {
                                    gender = it
                                },
                                enabled = enabled
                            )
                            TextFieldDateCustom(
                                modifier = Modifier.fillMaxWidth(),
                                value = birthDate,
                                label = "Tanggal Lahir",
                                onDateSelected = {
                                    birthDate = it

                                },
                                enabled = enabled
                            )
                            TextFieldCustom(
                                modifier = Modifier.fillMaxWidth(),
                                values = phone,
                                label = "No. Handphone",
                                onValueChange = {
                                    phone = it
                                },
                                keyboardType = KeyboardType.Number,
                                enabled = enabled
                            )
                            TextFieldCustom(
                                modifier = Modifier.fillMaxWidth(),
                                values = address,
                                label = "Alamat",
                                onValueChange = {
                                    address = it
                                },
                                keyboardType = KeyboardType.Text,
                                enabled = enabled
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
                    (state as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            is UiState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Tidak ada data",
                        style = MaterialTheme.typography.titleMedium,
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
            idUser = "",
            contentPadding = PaddingValues()
        )
    }
}