package id.co.mondo.ukhuwah.ui.screen.family

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.data.model.User
import id.co.mondo.ukhuwah.ui.common.UiState
import id.co.mondo.ukhuwah.ui.components.AppTopBar
import id.co.mondo.ukhuwah.ui.components.ParentCard
import id.co.mondo.ukhuwah.ui.components.SearchField
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.UserViewModel

@Composable
fun ParentScreen(
    navController: NavController,
    contentPadding: PaddingValues
) {
    val context = LocalContext.current

    val userViewModel: UserViewModel = viewModel()
    val userState by userViewModel.userState.collectAsState()

    val state = rememberPullToRefreshState()

    var query by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        userViewModel.getAllUser()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
    ) {
        AppTopBar(
            title = "Orang Tua",
            showBack = false,

            )
        Row(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SearchField(
                query = query,
                onQueryChange = {
                    query = it
                },
                modifier = Modifier.weight(1F)
            )
            Button(
                onClick = {
                    navController.navigate("register")

                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier.height(48.dp),
                contentPadding = PaddingValues(horizontal = 12.dp),
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        painter = painterResource(R.drawable.family),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Buat Akun",
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontSize = 10.sp
                        )
                    )
                }

            }
        }
        when (userState) {
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
                PullToRefreshBox(
                    isRefreshing = userViewModel.isRefreshing,
                    onRefresh = {
                        userViewModel.getAllUser(isRefresh = true)
                    },
                    state = state
                ) {
                    val users = (userState as UiState.Success<List<User>>).data
                    val sorted = users.sortedByDescending { it.created_at }


                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 12.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(sorted) {
                            ParentCard(
                                user = User(
                                    name = it.name,
                                    nik = it.nik,
                                    phone = it.phone,
                                    email = it.email,
                                    children = it.children
                                ),
                                onClick = {
                                    navController.navigate("profile-parent/${it.id_users}")
                                }
                            )
                        }
                    }
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
                    (userState as UiState.Error).message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}


@Preview(showBackground = true, device = PIXEL_4)
@Composable
fun PreviewParentScreen() {
    Innovillage6thTheme {
        ParentScreen(
            navController = rememberNavController(),
            contentPadding = PaddingValues()
        )
    }
}