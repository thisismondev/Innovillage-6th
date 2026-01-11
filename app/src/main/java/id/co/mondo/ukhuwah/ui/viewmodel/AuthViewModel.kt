package id.co.mondo.ukhuwah.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.mondo.ukhuwah.data.supabase.AuthService
import id.co.mondo.ukhuwah.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authService: AuthService = AuthService()
) : ViewModel() {


    var email: String by mutableStateOf("")
    var password: String by mutableStateOf("")
    var confirmPassword: String by mutableStateOf("")

    private val _loginState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val loginState: StateFlow<UiState<String>> = _loginState

    private val _logoutState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val logoutState: StateFlow<UiState<String>> = _logoutState

    private val _isLoggedIn = MutableStateFlow<Boolean?>(null)
    val isLoggedIn = _isLoggedIn.asStateFlow()


    init {
        viewModelScope.launch {
            _isLoggedIn.value = authService.restoreSession()
        }
    }

    fun login(email: String, password: String) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            if (email.isBlank()) {
                _loginState.value = UiState.Error("Email wajib diisi")
                return@launch
            }

            if (password.isBlank()) {
                _loginState.value = UiState.Error("Password wajib diisi")
                return@launch
            }
            val response = authService.login(email, password)
            response.onSuccess {
                Log.d("Login", "Login berhasil $email ")
                _isLoggedIn.value = true
                _loginState.value = UiState.Success("Login Berhasil")
            }.onFailure {
                val errorMessage =
                    if (it.message?.contains("invalid", true) == true) {
                        "Email atau kata sandi salah"
                    } else {
                        "Login gagal, silakan coba lagi"
                    }
                Log.d("Login", "Login gagal $email ")
                _isLoggedIn.value = false
                _loginState.value = UiState.Error(errorMessage)
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _isLoggedIn.value = false
            Log.d("Logout", "Logout berhasil : ${_isLoggedIn.value}")
            authService.logout()
            Log.d("Logout", "Logout berhasil : ${_isLoggedIn.value}")
        }
    }
}

