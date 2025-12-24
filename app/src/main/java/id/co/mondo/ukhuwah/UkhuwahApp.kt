package id.co.mondo.ukhuwah

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.components.BottomBar
import id.co.mondo.ukhuwah.ui.screen.AddScreen
import id.co.mondo.ukhuwah.ui.screen.DetailScreen
import id.co.mondo.ukhuwah.ui.screen.HistoryScreen
import id.co.mondo.ukhuwah.ui.screen.HomeScreen
import id.co.mondo.ukhuwah.ui.screen.LoginScreen
import id.co.mondo.ukhuwah.ui.screen.ParentScreen
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

@Composable
fun UkhuwahApp(startDestination: String) {


    val navController = rememberNavController()
    val authViewModel: AuthViewModel = viewModel()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val bottomBarRoutes = setOf(
        "home",
        "detail",
        "add",
        "history",
        "parent"
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarRoutes) {
                BottomBar(navController)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(padding),
        ) {
            composable("login") {
                LoginScreen(navController, authViewModel)
            }
            composable("home") {
                HomeScreen(navController)
            }
            composable ("detail") {
                DetailScreen()
            }
            composable ("history") {
                HistoryScreen()
            }
            composable ("parent") {
                ParentScreen()
            }
            composable("add"){
                AddScreen()
            }
        }
    }
}