package id.co.mondo.ukhuwah

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import id.co.mondo.ukhuwah.ui.components.BottomBar
import id.co.mondo.ukhuwah.ui.screen.HistoryScreen
import id.co.mondo.ukhuwah.ui.screen.HomeScreen
import id.co.mondo.ukhuwah.ui.screen.NotificationScreen
import id.co.mondo.ukhuwah.ui.screen.auth.LoginScreen
import id.co.mondo.ukhuwah.ui.screen.auth.RegisterScreen
import id.co.mondo.ukhuwah.ui.screen.detail.DetailScreen
import id.co.mondo.ukhuwah.ui.screen.family.AddChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.ParentScreen
import id.co.mondo.ukhuwah.ui.screen.family.ProfileChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.ProfileParentScreen
import id.co.mondo.ukhuwah.ui.screen.family.UpdateChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.health.DetailMpasiScreen
import id.co.mondo.ukhuwah.ui.screen.health.HealthScreen
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UkhuwahApp(startDestination: String, authViewModel: AuthViewModel) {


    val navController = rememberNavController()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        Log.d("NAV_DEBUG", "Current route: $currentRoute")
    }

    val bottomBarRoutes = setOf(
        "home",
        "detail",
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
                HomeScreen(navController, authViewModel)
            }
            composable("detail") {
                DetailScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("history") {
                HistoryScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("register") {
                RegisterScreen(
                    navController = navController,
                    authViewModel = authViewModel,
                    contentPadding = innerPadding
                )
            }
            composable("parent") {
                ParentScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("update-children") {
                UpdateChildrenScreen(
                    navController = navController,
                    paddingvalues = innerPadding
                )
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
            composable("health") {
                HealthScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("notification") {
                NotificationScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("detail-mpasi") {
                DetailMpasiScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
        }
    }
}