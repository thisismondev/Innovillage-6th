package id.co.mondo.ukhuwah.ui.screen.family

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.InsertChildren
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.GenderField
import id.co.mondo.ukhuwah.ui.components.SearchField
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.ChildViewModel
import id.co.mondo.ukhuwah.ui.viewmodel.UserViewModel

@Composable
fun AddChildrenScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {
    val context = LocalContext.current

    val userViewModel: UserViewModel = viewModel()
    val childViewModel: ChildViewModel = viewModel()
    val userState by userViewModel.userState.collectAsState()
    val insertNewChildState by childViewModel.insertNewChildState.collectAsState()

    val newMeasureState by childViewModel.newMeasureState.collectAsState()

    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }

    var selectUserId by remember { mutableStateOf<String?>(null) }
    val enabled = selectUserId != null


    var name by remember { mutableStateOf("") }
    var nik by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var tb by remember { mutableStateOf("") }
    var bb by remember { mutableStateOf("") }
    var lk by remember { mutableStateOf("") }
    var lila by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        userViewModel.getAllUser()
    }

    LaunchedEffect(insertNewChildState) {
        when(insertNewChildState) {
            is UiState.Success -> {
                Toast.makeText(
                    context,
                    "Berhasil Tambah anak baru",
                    Toast.LENGTH_SHORT
                ).show()


                name = ""
                nik = ""
                birthDate = ""
                gender = ""
                tb = ""
                bb = ""
                lk = ""
                lila = ""
            }
            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (insertNewChildState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit

        }
    }

    if (insertNewChildState is UiState.Loading) {
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

    val userList = when (userState) {
        is UiState.Success -> (userState as UiState.Success).data
        else -> emptyList()
    }

    val filteredUsers = if (searchQuery.isBlank()) {
        userList
    } else {
        userList.filter {
            it.name
                ?.contains(searchQuery, ignoreCase = true)
                ?: false
        }
    }

    val users = when (newMeasureState) {
        is UiState.Success -> (newMeasureState as UiState.Success).data
        else -> childViewModel.defaultSelectUser()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            Modifier
                .fillMaxSize()
        ) {
            AppTopBar(
                title = "Tambah Anak",
                showBack = true,
                onBackClick = {
                    if (isSearchMode) {
                        isSearchMode = false
                        searchQuery = ""
                    } else {
                        navController.popBackStack()
                    }
                },
                showSearch = true,
                onSearchClick = {
                    isSearchMode = true
                }
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        Text(
                            text = "Informasi Orang Tua",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Black
                        )
                        TextFieldCustom(
                            modifier = Modifier.fillMaxWidth(),
                            values = users.name ?: "",
                            label = "Nama Orang Tua",
                            onValueChange = {

                            },
                            keyboardType = KeyboardType.Text,
                            enabled = false
                        )
                        TextFieldDateCustom(
                            modifier = Modifier.fillMaxWidth(),
                            value = users.birth ?: "",
                            label = "Tanggal Lahir",
                            onDateSelected = {

                            },
                            enabled = false
                        )
                        TextFieldCustom(
                            modifier = Modifier.fillMaxWidth(),
                            values = users.phone ?: "",
                            label = "No. Handphone",
                            onValueChange = {

                            },
                            keyboardType = KeyboardType.Number,
                            enabled = false
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
                        TextFieldCustom(
                            modifier = Modifier.fillMaxWidth(),
                            values = name,
                            label = "Nama",
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
                            onDateSelected = { birthDate = it },
                            enabled = enabled
                        )
                    }
                }
                item {
                    Text(
                        text = "Data kelahiran",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.Black
                    )
                    Spacer(Modifier.height(12.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            TextFieldCustom(
                                modifier = Modifier.weight(1f),
                                values = tb,
                                label = "TB",
                                onValueChange = {
                                    tb = it
                                },
                                keyboardType = KeyboardType.Decimal,
                                enabled = enabled
                            )
                            TextFieldCustom(
                                modifier = Modifier.weight(1f),
                                values = bb,
                                label = "BB",
                                onValueChange = {
                                    bb = it
                                },
                                keyboardType = KeyboardType.Decimal,
                                enabled = enabled
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            TextFieldCustom(
                                modifier = Modifier.weight(1f),
                                values = lk,
                                label = "LK",
                                onValueChange = {
                                    lk = it
                                },
                                keyboardType = KeyboardType.Decimal,
                                enabled = enabled
                            )
                            TextFieldCustom(
                                modifier = Modifier.weight(1f),
                                values = lila,
                                label = "LiLA",
                                onValueChange = {
                                    lila = it
                                },
                                keyboardType = KeyboardType.Decimal,
                                enabled = enabled
                            )
                        }
                    }

                }
                item {
                    ButtonCustom(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Simpan",
                        onClick = {
                            val nikValue = nik.trim().ifEmpty { null }
                            val child = InsertChildren(
                                users_id = selectUserId,
                                name = name,
                                nik = nikValue,
                                gender = gender,
                                birth = birthDate,
                                heightCm = tb.toDoubleOrNull(),
                                weightKg = bb.toDoubleOrNull(),
                                armCm = lk.toDoubleOrNull(),
                                headCm = lila.toDoubleOrNull(),
                            )
                            childViewModel.insertNewChildUser(child)
                        },
                        enabled = enabled
                    )
                }
            }
        }
        if (isSearchMode) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        IconButton(
                            onClick = {
                                isSearchMode = false
                                searchQuery = ""
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        SearchField(
                            query = searchQuery,
                            onQueryChange = { searchQuery = it }
                        )
                    }

                    if (filteredUsers.isNotEmpty()) {
                        OutlinedCard {
                            Column {
                                filteredUsers.forEach { user ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                // ✅ pilih user
                                                childViewModel.selectUserWithChild(user.id_users)

                                                selectUserId = user.id_users

                                                isSearchMode = false
                                                searchQuery = ""
                                            }
                                            .padding(12.dp)
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.spacedBy(2.dp)
                                        ) {
                                            Text(
                                                text = user.name ?: "-",
                                                style = MaterialTheme.typography.titleMedium,
                                                color = Color.DarkGray
                                            )
                                            Text(
                                                text = user.nik ?: "-",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = Color.Gray
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        Text(
                            text = "User tidak ditemukan",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
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