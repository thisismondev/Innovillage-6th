package id.co.mondo.ukhuwah.ui.screen

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.data.model.menuItemsKader
import id.co.mondo.ukhuwah.data.utils.getAvailableMonthsByYear
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.ButtonCustom
import id.co.mondo.ukhuwah.ui.components.CardKader
import id.co.mondo.ukhuwah.ui.components.FilterDropdown
import id.co.mondo.ukhuwah.ui.components.HeaderKader
import id.co.mondo.ukhuwah.ui.components.NavItemCard
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel
import id.co.mondo.ukhuwah.ui.viewmodel.ChildViewModel
import id.co.mondo.ukhuwah.ui.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {

    LaunchedEffect(Unit) {
        Log.d("HomeScreen", "Home Screen terpanggil")
    }

    val context = LocalContext.current


    val childViewModel: ChildViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val childState by childViewModel.countChildMeasureState.collectAsState()
    val exportState by userViewModel.exportState.collectAsState()

    val logoutState by authViewModel.logoutState.collectAsState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    val selectedYear = "2026"
    val monthList = remember(selectedYear) {
        getAvailableMonthsByYear(selectedYear)
    }
    var selectedMonth by remember { mutableStateOf(monthList.first()) }

    LaunchedEffect(exportState) {
        when (exportState) {
            is UiState.Success -> {
                Toast.makeText(
                    context,
                    "File berhasil disimpan di Download",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (exportState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit
        }
    }


    LaunchedEffect(Unit) {
        childViewModel.getCountChildMeasure()
    }


    val countChild = when (childState) {
        is UiState.Success -> (childState as UiState.Success).data.countChild
        else -> "-"
    }

    val countMeasure = when (childState) {
        is UiState.Success -> (childState as UiState.Success).data.countMeasure
        else -> "-"
    }



    LaunchedEffect(logoutState) {
        when (logoutState) {
            is UiState.Success -> {
                Toast.makeText(
                    context,
                    "Berhasil Keluar",
                    Toast.LENGTH_SHORT
                ).show()
            }

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (logoutState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

            else -> Unit

        }
    }

    if (logoutState is UiState.Loading) {
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

    if (exportState is UiState.Loading) {
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



    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(
                    text = "Konfirmasi",
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            text = {
                Text(text = "Yakinki mau Keluar?")
            },
            confirmButton = {
                Button(
                    onClick = {
                        showDialog = false
                        authViewModel.logout()
                    }
                ) {
                    Text(
                        text = "Keluar",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = { showDialog = false }
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
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp)
                )
                .padding(horizontal = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            HeaderKader(
                onClickNotification = {
                    navController.navigate("notification")
                },
                onClickLogout = {
                    showDialog = true
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .offset(y = -20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)

        ) {
            CardKader(
                countChild = countChild.toString(),
                countMeasure = countMeasure.toString()
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(20.dp)
            ) {
                items(menuItemsKader) { item ->
                    NavItemCard(
                        title = item.title,
                        color = item.color,
                        icon = item.icon,
                        onClick = {
                            if (item.title == "Export Data Anak") {
                                showBottomSheet = true
                            } else {
                                navController.navigate(item.routes)
                            }

                        }
                    )

                }
            }
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Export Data Anak",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )
                        Text(
                            text = "Silahkan Pilih Bulan dan Tahun yang akan di Export",
                            style = MaterialTheme.typography.titleSmall,
                            color = Color.Black
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            FilterDropdown(
                                selectedValue = selectedMonth,
                                items = monthList,
                                onItemSelected = {
                                    selectedMonth = it
                                },
                                modifier = Modifier.weight(1F)
                            )

                        }
                        ButtonCustom(
                            onClick = {
                                scope.launch {
                                    sheetState.hide()
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                        userViewModel.exportUsersByMonth("Februari")
                                    }
                                }
                                Log.d("HomeScreen", "btn di klik")
                            },
                            label = "Export",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }


    }

}

@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    Innovillage6thTheme {
        HomeScreen(navController = rememberNavController(), authViewModel = viewModel())
    }
}