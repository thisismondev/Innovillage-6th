package id.co.mondo.ukhuwah.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.mondo.ukhuwah.data.model.InsertUser
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

    private val _isCheckingSession = MutableStateFlow(true)
    val isCheckingSession = _isCheckingSession.asStateFlow()

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()

    private val _userRole = MutableStateFlow<String?>(null)
    val userRole = _userRole.asStateFlow()

    init {
        viewModelScope.launch {
            val loggedIn = authService.restoreSession()
            if (loggedIn) {
                loadUserRole()
            } else {
                _isLoggedIn.value = false
                _isCheckingSession.value = false
            }
        }
    }

    private suspend fun loadUserRole() {
        val result = userService.getUserRole()
        result.onSuccess { user ->
            Log.d("AuthViewModel", "Role user = $user")
            _userRole.value = user.role
            _isLoggedIn.value = true
        }.onFailure {
            Log.e("AuthViewModel", "Gagal ambil role", it)
            _isLoggedIn.value = false
            _userRole.value = null
            authService.logout()
        }
        _isCheckingSession.value = false
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
                loadUserRole()
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
            authService.logout()
            _userRole.value = null
            _isLoggedIn.value = false
            Log.d("Logout", "Logout berhasil : ${_isLoggedIn.value}")
        }
    }

    fun createAccount(
        email: String,
        password: String,
        confirmPassword: String,
        user: InsertUser
    ) {
        _registerState.value = UiState.Loading

        viewModelScope.launch {

            if (email.isBlank()) {
                _registerState.value = UiState.Error("Email wajib diisi")
                return@launch
            }

            if (password.length < 6) {
                _registerState.value = UiState.Error("Password minimal 6 karakter")
                return@launch
            }
            if (password != confirmPassword) {
                _registerState.value = UiState.Error("Password tidak sama")
                return@launch
            }

            if (
                user.name.isNullOrBlank() ||
                user.nik.isNullOrBlank() ||
                user.gender.isNullOrBlank() ||
                user.birth.isNullOrBlank() ||
                user.phone.isNullOrBlank() ||
                user.address.isNullOrBlank()
            ) {
                _registerState.value =
                    UiState.Error("Semua data user wajib diisi")
                return@launch
            }

            val responseAuth = authService.register(email, password)
            responseAuth.onSuccess { account ->
                val setUser = InsertUser(
                    id_users = account.id,
                    email = account.email,
                    name = user.name,
                    nik = user.nik,
                    gender = user.gender,
                    birth = user.birth,
                    phone = user.phone,
                    address = user.address
                )

                val responseUser = userService.createUser(setUser)
                responseUser.onSuccess {
                    _registerState.value = UiState.Success("Buat Akun User Berhasil")
                    Log.d("CreateAccount", "Buat Akun user berhasil $setUser")
                }.onFailure {
                    Log.d("CreateAccount", "Gagal Menyimpan Profile $setUser")
                    val deleteAuth = authService.deleteUser(account.id)
                    Log.d("CreateAccount", "Hapus Akun di Auth berhasil $deleteAuth")
                    _registerState.value = UiState.Error("Gagal Membuat Akun User")
                }
            }.onFailure {
                Log.d("CreateAccount", "Error: ${it.message}")
                _registerState.value = UiState.Error(it.message ?: "Gagal Membuat Akun")
                return@launch
            }

        }
    }

    fun resetRegisterState() {
        _registerState.value = UiState.Empty
    }


}

