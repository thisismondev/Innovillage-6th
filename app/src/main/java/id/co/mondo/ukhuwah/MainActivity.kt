package id.co.mondo.ukhuwah

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            viewModel.isLoggedIn.value == null
        }

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Innovillage6thTheme {
                val isLoggedIn by viewModel.isLoggedIn.collectAsState()
                when (isLoggedIn) {
                    null -> {}
                    true -> {
                        UkhuwahApp(startDestination = "home", authViewModel = viewModel)
                    }
                    false -> {
                        UkhuwahApp(startDestination = "login", authViewModel = viewModel)
                    }
                }
            }
        }
    }
}
