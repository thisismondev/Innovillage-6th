package id.co.mondo.ukhuwah

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import id.co.mondo.ukhuwah.ui.screen.auth.LoginScreen
import id.co.mondo.ukhuwah.ui.screen.auth.RegisterScreen
import id.co.mondo.ukhuwah.ui.screen.family.AddChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.ParentScreen
import id.co.mondo.ukhuwah.ui.screen.family.ProfileChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.ProfileParentScreen
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
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,

            ) {
            composable("login") {
                LoginScreen(navController, authViewModel)
            }
            composable("home") {
                HomeScreen(navController)
            }
            composable("detail") {
                DetailScreen()
            }
            composable("history") {
                HistoryScreen()
            }
            composable("register") {
                RegisterScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("parent") {
                ParentScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("add") {
                AddScreen()
            }
            composable("profile-parent") {
                ProfileParentScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("profile-children") {
                ProfileChildrenScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("add-children") {
                AddChildrenScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
        }
    }
}