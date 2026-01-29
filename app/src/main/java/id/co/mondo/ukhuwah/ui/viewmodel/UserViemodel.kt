package id.co.mondo.ukhuwah.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.mondo.ukhuwah.data.model.BalitaData
import id.co.mondo.ukhuwah.data.model.Children
import id.co.mondo.ukhuwah.data.model.User
import id.co.mondo.ukhuwah.data.retrofit.ApiConfig
import id.co.mondo.ukhuwah.data.supabase.UserService
import id.co.mondo.ukhuwah.data.utils.calculateAgeFromBirthToNow
import id.co.mondo.ukhuwah.data.utils.calculateAgeInMonths
import id.co.mondo.ukhuwah.data.utils.calculateDateInMonth
import id.co.mondo.ukhuwah.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userService: UserService = UserService(),
) : ViewModel() {

    private val apiService = ApiConfig.getApiService()

    private val _userState = MutableStateFlow<UiState<List<User>>>(UiState.Empty)
    val userState: StateFlow<UiState<List<User>>> = _userState

    private val _userId = MutableStateFlow<UiState<User>>(UiState.Empty)
    val userId: StateFlow<UiState<User>> = _userId

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
            response.onSuccess { user ->

                if (user.id_users != id && user.id_users == null) {
                    _userId.value = UiState.Error("User tidak ditemukan")
                }

                val mapUser = User(
                    id_users = user.id_users,
                    name = user.name,
                    nik = user.nik,
                    email = user.email,
                    gender = user.gender,
                    birth = user.birth,
                    phone = user.phone,
                    address = user.address,
                    children = user.children?.map { children ->
                        Children(
                            id = children.id,
                            name = children.name,
                            ageResult = calculateAgeFromBirthToNow(
                                birth = children.birth ?: ""
                            )
                        )
                    }

                )

                Log.d("UserViewModel", "Get user by id sukses: $mapUser")
                _userId.value = UiState.Success(mapUser)
            }.onFailure {
                Log.d("UserViewModel", "Get user by id GAGAL: $it")
                _userId.value = UiState.Error("Gagal mendapatkan data")
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

    private val _exportState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val exportState = _exportState.asStateFlow()

    fun exportUsersByMonth(month: String) {
        _exportState.value = UiState.Loading

        viewModelScope.launch {
            val result = userService.exportUsers()

            result.onSuccess { children ->

                // 1️⃣ MAPPING SEMUA CHILD → BALITADATA
                val requestList = children.map { child ->
                    val measurement = child.measurements?.firstOrNull{
                        calculateDateInMonth(it.measured_at ?: "") == month
                    }

                    val ageMonth = calculateAgeInMonths(child.birth ?: "")

                    BalitaData(
                        nik = child.nik ?: "",
                        namaAnak = child.name ?: "",
                        tglLahir = child.birth ?: "",
                        umur = ageMonth,
                        jk = child.gender ?: "",
                        namaOrtu = child.users?.name ?: "",
                        nikOrtu = child.users?.nik ?: "",
                        hp = child.users?.phone ?: "",
                        alamat = child.users?.address ?: "",
                        bulan = month,
                        bb = measurement?.weightKg,
                        tb = measurement?.heightCm,
                        lila = measurement?.armCm,
                        lkep = measurement?.headCm,
                        status = measurement?.status
                    )
                }

                try {
                    // 2️⃣ KIRIM SEKALI SAJA
                    val response = apiService.kirimData(requestList)

                    if (response.isSuccessful && response.body()?.success == true) {
                        _exportState.value =
                            UiState.Success("Export ${response.body()?.processed} data berhasil")
                    } else {
                        _exportState.value =
                            UiState.Error(response.body()?.message ?: "Gagal export")
                    }

                } catch (e: Exception) {
                    _exportState.value =
                        UiState.Error("Gagal mengirim data ke Excel")
                }

            }.onFailure {
                _exportState.value =
                    UiState.Error("Gagal mengambil data dari Supabase")
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


