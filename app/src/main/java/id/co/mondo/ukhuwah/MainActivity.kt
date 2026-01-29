package id.co.mondo.ukhuwah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import id.co.mondo.ukhuwah.ui.theme.Innovillage6thTheme
import id.co.mondo.ukhuwah.ui.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
           viewModel.isCheckingSession.value
        }

        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            Innovillage6thTheme {
               UkhuwahApp(authViewModel = viewModel)
            }
        }
    }
}
