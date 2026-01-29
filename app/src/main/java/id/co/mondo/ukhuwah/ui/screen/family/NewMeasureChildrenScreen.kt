package id.co.mondo.ukhuwah.ui.screen.family

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.data.model.NewMeasure
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.CardListCard
import id.co.mondo.ukhuwah.ui.components.RadioField
import id.co.mondo.ukhuwah.ui.components.SearchField
import id.co.mondo.ukhuwah.ui.components.StatusIndicator
import id.co.mondo.ukhuwah.ui.components.TextFieldCustom
import id.co.mondo.ukhuwah.ui.components.TextFieldDateCustom
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.ChildViewModel
import id.co.mondo.ukhuwah.ui.viewmodel.UserViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewMeasureChildrenScreen(
    navController: NavController,
    paddingvalues: PaddingValues
) {
    val context = LocalContext.current

    val childViewModel: ChildViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val newMeasureState by childViewModel.newMeasureState.collectAsState()
    val userState by userViewModel.userState.collectAsState()
    val insertNewMeasureState by childViewModel.insertNewMeasureState.collectAsState()


    var measureDate by remember { mutableStateOf("") }
    var tb by remember { mutableStateOf("") }
    var bb by remember { mutableStateOf("") }
    var lk by remember { mutableStateOf("") }
    var lila by remember { mutableStateOf("") }

    var metodeInput by remember { mutableStateOf("Manual") }

    var isSearchMode by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var selectChildId by remember { mutableStateOf<Int?>(null) }
    val enabled = selectChildId != null


    LaunchedEffect(Unit) {
        userViewModel.getAllUser()
    }

    LaunchedEffect(insertNewMeasureState) {
        when(insertNewMeasureState) {
            is UiState.Success -> {
                Toast.makeText(
                    context,
                    "Berhasil Update pertumbuhan",
                    Toast.LENGTH_SHORT
                ).show()

                measureDate = ""
                tb = ""
                bb = ""
                lk = ""
                lila = ""
            }
            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (insertNewMeasureState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> Unit

        }
    }


    if (newMeasureState is UiState.Loading) {
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

    if (insertNewMeasureState is UiState.Loading) {
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
            .padding(paddingvalues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AppTopBar(
                title = "Update Pertumbuhan Anak",
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
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
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
                        if (users.children.isNullOrEmpty()) {
                            Text(
                                text = "Belum ada data anak",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        } else {
                            users.children.forEach { child ->
                                val isSelected = selectChildId == child.id
                                CardListCard(
                                    name = child.name ?: "",
                                    ageResult = child.ageResult,
                                    onClick = {
                                        selectChildId = child.id
                                    },
                                    showIcon = false,
                                    select = isSelected
                                )
                            }
                        }
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
                            value = measureDate,
                            label = "Tanggal Pengukuran",
                            onDateSelected = { measureDate = it },
                            enabled = enabled
                        )
                        RadioField(
                            selectedRadio = metodeInput,
                            onRadioSelected = {
                                metodeInput = it
                            },
                            enabled = enabled
                        )
                        if (metodeInput == "Manual") {
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

                                        },
                                        enabled = enabled
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
                                }
                            }
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
                            val measure = NewMeasure(
                                children_id = selectChildId,
                                measured_at = measureDate,
                                heightCm = tb.toDoubleOrNull(),
                                weightKg = bb.toDoubleOrNull(),
                                armCm = lk.toDoubleOrNull(),
                                headCm = lila.toDoubleOrNull(),
                            )
                            childViewModel.insertNewMeasure(measure)
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

                                                selectChildId = null

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
fun PreviewUpdateChildrenScreen() {
    Innovillage6thTheme {

        NewMeasureChildrenScreen(
            navController = rememberNavController(),
            paddingvalues = PaddingValues()
        )
    }
}