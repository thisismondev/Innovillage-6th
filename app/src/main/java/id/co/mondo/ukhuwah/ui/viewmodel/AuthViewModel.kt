package id.co.mondo.ukhuwah.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.mondo.ukhuwah.data.model.User
import id.co.mondo.ukhuwah.data.supabase.AuthService
import id.co.mondo.ukhuwah.data.supabase.UserService
import id.co.mondo.ukhuwah.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authService: AuthService = AuthService(),
    private val userService: UserService = UserService()
) : ViewModel() {

    private val _loginState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val loginState: StateFlow<UiState<String>> = _loginState

    private val _registerState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val registerState: StateFlow<UiState<String>> = _registerState

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

    fun createAccount(
        email: String,
        password: String,
        confirmPassword: String,
        user: User
    ) {
        _registerState.value = UiState.Loading

        viewModelScope.launch {

//            if (email.isBlank()) {
//                _registerState.value = UiState.Error("Email wajib diisi")
//                return@launch
//            }
//
//            if (password.isBlank()) {
//                _registerState.value = UiState.Error("Password wajib diisi")
//                return@launch
//            }
//
            if (password != confirmPassword) {
                _registerState.value = UiState.Error("Password tidak sama")
                return@launch
            }

            authService.register(email, password)
                .onFailure {
                    Log.d("CreateAccount", "Gagal Membuat Akun $email")
                    _registerState.value = UiState.Error("Gagal Membuat Akun")
                    return@launch
                }

            userService.createUser(user)
                .onSuccess {
                    _registerState.value = UiState.Success("Buat Akun Berhasil")
                    Log.d("CreateAccount", "Buat Akun berhasil $email")
                }
                .onFailure {
                    Log.d("CreateAccount", "Gagal Menyimpan Profile $email")
                    _registerState.value = UiState.Error("Gagal menyimpan Profile")
                }
        }
    }

    fun resetRegisterState() {
        _registerState.value = UiState.Empty
    }


}

