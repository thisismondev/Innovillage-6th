package id.co.mondo.ukhuwah

import android.util.Log
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import id.co.mondo.ukhuwah.ui.components.BottomBar
import id.co.mondo.ukhuwah.ui.components.BottomBarParent
import id.co.mondo.ukhuwah.ui.screen.HistoryScreen
import id.co.mondo.ukhuwah.ui.screen.HomeParentScreen
import id.co.mondo.ukhuwah.ui.screen.HomeScreen
import id.co.mondo.ukhuwah.ui.screen.NotificationScreen
import id.co.mondo.ukhuwah.ui.screen.ProfileScreen
import id.co.mondo.ukhuwah.ui.screen.auth.LoginScreen
import id.co.mondo.ukhuwah.ui.screen.auth.RegisterScreen
import id.co.mondo.ukhuwah.ui.screen.detail.DetailScreen
import id.co.mondo.ukhuwah.ui.screen.family.AddChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.NewMeasureChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.ParentScreen
import id.co.mondo.ukhuwah.ui.screen.family.ProfileChildrenScreen
import id.co.mondo.ukhuwah.ui.screen.family.ProfileParentScreen
import id.co.mondo.ukhuwah.ui.screen.health.DetailMpasiScreen
import id.co.mondo.ukhuwah.ui.screen.health.HealthScreen
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

@Composable
fun UkhuwahApp( authViewModel: AuthViewModel) {


    val navController = rememberNavController()


    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    LaunchedEffect(currentRoute) {
        Log.d("NAV_DEBUG", "Current route: $currentRoute")
    }

    val isLoggedIn by authViewModel.isLoggedIn.collectAsState()
    val userRole by authViewModel.userRole.collectAsState()

    LaunchedEffect(isLoggedIn, userRole) {
        if (!isLoggedIn) {
            navController.navigate("login") {
                popUpTo(0) { inclusive = true }
            }
            return@LaunchedEffect
        }

        if (userRole == null) {
            Log.d("NAV_DEBUG", "Menunggu role...")
            return@LaunchedEffect
        }

        val target = if (userRole == "Parent") {
            "home_parent"
        } else {
            "home"
        }

        navController.navigate(target) {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
        }
    }

    val routes = setOf(
        "home",
        "detail",
        "history",
        "parent",
        "home_parent",
        "profile"
    )

    Scaffold(
        bottomBar = {
            if (currentRoute in routes){
                when (userRole) {
                    "Admin" -> BottomBar(navController)
                    "Parent" -> BottomBarParent(navController)
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination ="login",

            ) {
//            Auth
            composable("login") {
                LoginScreen(navController, authViewModel)
            }
            composable("register") {
                RegisterScreen(
                    navController = navController,
                    authViewModel = authViewModel,
                    contentPadding = innerPadding
                )
            }
//            Kader & Orang tua
            composable(route = "detail") {
                DetailScreen(
                    navController = navController,
                    contentPadding = innerPadding,
                )
            }
            composable("history") {
                HistoryScreen(
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
//            Parent
            composable("home_parent") {
                HomeParentScreen(
                    navController = navController,
                    contentPadding = innerPadding
                )
            }
            composable("profile") {
                ProfileScreen(
                    navController = navController,
                    paddingValues = innerPadding,
                    authViewModel = authViewModel
                )
            }
//            Kader
            composable("home") {
                HomeScreen(navController, authViewModel)
            }

            composable("add-children") {
                AddChildrenScreen(
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
            composable("update-children") {
                NewMeasureChildrenScreen(
                    navController = navController,
                    paddingvalues = innerPadding
                )
            }
            composable("profile-parent/{id}") {
                val id = it.arguments?.getString("id")
                ProfileParentScreen(
                    navController = navController,
                    idUser = id ?: "",
                    contentPadding = innerPadding
                )
            }
            composable(
                route = "profile-children/{id}",
                arguments = listOf(
                    navArgument("id") {
                        type = NavType.IntType
                    }
                )
            ) {
                val id = it.arguments?.getInt("id")
                ProfileChildrenScreen(
                    navController = navController,
                    contentPadding = innerPadding,
                    id = id ?: 0
                )
            }


        }
    }
}