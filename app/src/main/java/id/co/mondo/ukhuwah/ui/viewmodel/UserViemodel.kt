package id.co.mondo.ukhuwah.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.mondo.ukhuwah.data.model.Children
import id.co.mondo.ukhuwah.data.model.MeasureWithChild
import id.co.mondo.ukhuwah.data.model.User
import id.co.mondo.ukhuwah.data.supabase.AuthService
import id.co.mondo.ukhuwah.data.supabase.UserService
import id.co.mondo.ukhuwah.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userService: UserService = UserService(),
    private val authService: AuthService = AuthService()
) : ViewModel() {

    private val _userState = MutableStateFlow<UiState<List<User>>>(UiState.Empty)
    val userState: StateFlow<UiState<List<User>>> = _userState

    private val _childState = MutableStateFlow<UiState<List<Children>>>(UiState.Empty)
    val childState: StateFlow<UiState<List<Children>>> = _childState

    private val _measureChildState = MutableStateFlow<UiState<List<MeasureWithChild>>>(UiState.Empty)
    val measureChildState: StateFlow<UiState<List<MeasureWithChild>>> = _measureChildState

    private val _userId = MutableStateFlow<UiState<User>>(UiState.Empty)
    val userId: StateFlow<UiState<User>> = _userId

    private val _childId = MutableStateFlow<UiState<Children>>(UiState.Empty)
    val childId: StateFlow<UiState<Children>> = _childId


    var isRefreshing by mutableStateOf(false)
        private set


    fun getAllUser(isRefresh: Boolean = false) {
        viewModelScope.launch {
            if (isRefreshing) {
                isRefreshing = true
            } else {
                _userState.value = UiState.Loading
            }
            val response = userService.getAllUsers()
            response.onSuccess {
                Log.d("UserViewModel", "Get all users sukses: $it")
                _userState.value = UiState.Success(it)
            }.onFailure {
                Log.d("UserViewModel", "Get all users GAGAL: $it")
                _userState.value = UiState.Error("Gagal mendapatkan data")
            }
            isRefreshing = false
        }

    }

    fun getUserById(id: String) {
        _userId.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getUserById(id)
            response.onSuccess {
                Log.d("UserViewModel", "Get user by id sukses: $it")
                _userId.value = UiState.Success(it)
            }.onFailure {
                Log.d("UserViewModel", "Get user by id GAGAL: $it")
                _userId.value = UiState.Error("Gagal mendapatkan data")
            }

        }
    }

    fun getAllMeasureChild() {
        _measureChildState.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getAllChildWithMeasure()
            response.onSuccess {
                val allMeasure = it.flatMap { children ->
                    children.measurements?.map { measure ->
                        MeasureWithChild(
                            id = children.id,
                            name = children.name,
                            measurements = measure
                        )
                    } ?: emptyList()
                }
                _measureChildState.value = UiState.Success(allMeasure)
                Log.d("UserViewModel", "Get all children with measure sukses: $allMeasure")
            }.onFailure {
                _measureChildState.value = UiState.Error("Gagal mendapatkan data")
                Log.d("UserViewModel", "Get all children with measure GAGAL: $it")
            }
        }
    }

    fun getAllChild(){
        _childState.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getAllChildWithMeasure()
            response.onSuccess {
                _childState.value = UiState.Success(it)
                Log.d("UserViewModel", "Get all children sukses: $it")
            }.onFailure {
                _childState.value = UiState.Error("Gagal mendapatkan data")
                Log.d("UserViewModel", "Get all children GAGAL: $it")
            }
        }
    }

    fun getChildById(id: Int) {
        Log.d("UserViewModel", "Get child by Id: $id")
        _childId.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getChildById(id)
            response.onSuccess {
                _childId.value = UiState.Success(it)
                Log.d("UserViewModel", "Get child by Id sukses: $it")
            }.onFailure {
                _childId.value = UiState.Error("Gagal mendapatkan data")
                Log.d("UserViewModel", "Get child by Id GAGAL: $it")
            }
        }
    }

    fun updateUser(user: User) {
        _userId.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.updateUser(user)
            response.onSuccess {
                _userId.value = UiState.Success(it)
                Log.d("UserViewModel", "Update profil sukses: $it")
            }.onFailure {
                _userId.value = UiState.Error("Gagal update profil")
                Log.d("UserViewModel", "Update profil GAGAL: $it")

            }


        }
    }

    fun updateChild(child: Children) {
        _childId.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.updateChild(child)
            response.onSuccess {
                _childId.value = UiState.Success(it)
                Log.d("UserViewModel", "Update profile children sukses: $it")
            }.onFailure {
                _childId.value = UiState.Error("Gagal update profil")
                Log.d("UserViewModel", "Update profil children GAGAL: $it")
            }
        }
    }
}
//            if (profileResult.isFailure) {
//                _userId.value = UiState.Error("Gagal update profil")
//                return@launch
//            }

//                if (emailResult.isFailure) {
//                    _userId.value = UiState.Error(
//                        "Profil tersimpan, tapi email perlu verifikasi"
//                    )
//                    return@launch
//                }
//


