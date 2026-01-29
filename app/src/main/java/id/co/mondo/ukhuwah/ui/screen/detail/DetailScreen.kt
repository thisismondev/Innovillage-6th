package id.co.mondo.ukhuwah.ui.screen.detail

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.SelectChildren
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.theme.Pink4
import id.co.mondo.ukhuwah.ui.viewmodel.ChildViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DetailScreen(
    navController: NavController,
    contentPadding: PaddingValues,

//    childIdFromNav: Int
) {
    val context = LocalContext.current

    val childViewModel: ChildViewModel = viewModel()
    val measuredChildState by childViewModel.childWithMeasureState.collectAsState()
    val childState by childViewModel.childState.collectAsState()


    val tabs = listOf("Statistik", "Perjalanan")
    var selectedTabIndex by remember { mutableStateOf(0) }


    var selectedChild by remember { mutableStateOf(-1) }

    LaunchedEffect(Unit) {
        childViewModel.getAllChild()
    }

    val childrenForSelect = when (childState) {
        is UiState.Success -> (childState as UiState.Success).data
        else -> emptyList()
    }

    val child = childrenForSelect
        .firstOrNull { it.id == selectedChild }
        ?.name ?: "Pilih Anak"


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        AppTopBar(
            title = "Riwayat Pertumbuhan",
            showBack = false
        )
        SelectChildren(
            selectedChild = child,
            onChildSelected = { id ->
                selectedChild = id
                childViewModel.getChildMeasure(id)
            },
            children = childrenForSelect
        )
        Spacer(Modifier.height(12.dp))
        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                val selected = selectedTabIndex == index

                Tab(
                    selected = selected,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier
                        .background(
                            color = if (selected)
                                Pink4
                            else
                                Color.White,
                        ),
                    text = {
                        Text(
                            text = title,
                            color = if (selected)
                                Color.Black
                            else
                                Color.Gray,
                            style = if (selected) {
                                MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold
                                )
                            } else {
                                MaterialTheme.typography.titleMedium
                            }
                        )
                    }
                )
            }
        }
        when (measuredChildState) {
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

            is UiState.Success -> {
                val child = (measuredChildState as UiState.Success).data

                when (selectedTabIndex) {
                    0 -> {
                        StatisticContent(
                            navController = navController,
                            child = child
                        )
                    }

                    1 -> GrowthContent()
                }
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

            is UiState.Error -> {
                Toast.makeText(
                    context,
                    (measuredChildState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    Innovillage6thTheme {
        DetailScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues(),
//            childIdFromNav = -1
        )
    }
}