package id.co.mondo.ukhuwah.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.mondo.ukhuwah.data.model.AgeResult
import id.co.mondo.ukhuwah.data.model.ChildWithMeasure
import id.co.mondo.ukhuwah.data.model.Children
import id.co.mondo.ukhuwah.data.model.CountChildMeasure
import id.co.mondo.ukhuwah.data.model.InsertChildren
import id.co.mondo.ukhuwah.data.model.NewMeasure
import id.co.mondo.ukhuwah.data.model.User
import id.co.mondo.ukhuwah.data.supabase.UserService
import id.co.mondo.ukhuwah.data.utils.calculateAgeFromBirthToMeasure
import id.co.mondo.ukhuwah.data.utils.calculateAgeFromBirthToNow
import id.co.mondo.ukhuwah.data.utils.formatDateToMonthYear
import id.co.mondo.ukhuwah.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChildViewModel(
    private val userService: UserService = UserService(),
) : ViewModel() {

    private val _newMeasureState = MutableStateFlow<UiState<User>>(UiState.Empty)
    val newMeasureState: StateFlow<UiState<User>> = _newMeasureState

    private val _childState = MutableStateFlow<UiState<List<Children>>>(UiState.Empty)
    val childState: StateFlow<UiState<List<Children>>> = _childState

    private val _countChildMeasrueState = MutableStateFlow<UiState< CountChildMeasure>>(UiState.Empty)
    val countChildMeasureState: StateFlow<UiState<CountChildMeasure>> = _countChildMeasrueState

    private val _allChilWithMeasuredState =
        MutableStateFlow<UiState<List<ChildWithMeasure>>>(UiState.Empty)
    val allChildWithMeasureState: StateFlow<UiState<List<ChildWithMeasure>>> =
        _allChilWithMeasuredState

    private val _insertNewMeasureState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val insertNewMeasureState: StateFlow<UiState<String>> = _insertNewMeasureState

    private val _insertNewChildState = MutableStateFlow<UiState<String>>(UiState.Empty)
    val insertNewChildState: StateFlow<UiState<String>> = _insertNewChildState

    private val _childWithMeasureState = MutableStateFlow<UiState<ChildWithMeasure>>(UiState.Empty)
    val childWithMeasureState: StateFlow<UiState<ChildWithMeasure>> = _childWithMeasureState

    private val _childId = MutableStateFlow<UiState<Children>>(UiState.Empty)
    val childId: StateFlow<UiState<Children>> = _childId

    init {
        _childWithMeasureState.value = UiState.Success(defaultChildMeasure())
    }


    fun getAllChild() {
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

    fun getCountChildMeasure(){
        viewModelScope.launch {
            val response = userService.getAllChildWithMeasure()
            response.onSuccess { child ->
                val countChild = child.size
                val countMeasure = child.flatMap { it.measurements ?: emptyList() }.size
                val count = CountChildMeasure(
                    countChild = countChild,
                    countMeasure = countMeasure
                )
                _countChildMeasrueState.value = UiState.Success(count)
                Log.d("UserViewModel", "Get count child & measure sukses: $count")
            }.onFailure {
                _countChildMeasrueState.value = UiState.Error("Gagal mendapatkan data")
                Log.d("UserViewModel", "Get count child & measure GAGAL: $it")
            }
        }
    }

    fun getChildById(id: Int) {
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

    private fun defaultChildMeasure(): ChildWithMeasure {
        return ChildWithMeasure(
            children_id = null,
            name = "-",
            id = -1,
            birth = null,
            ageResult = AgeResult(
                years = 0,
                months = 0,
                days = 0
            ),
            measured_at = "-",
            heightCm = 0.0,
            weightKg = 0.0,
            armCm = 0.0,
            headCm = 0.0
        )
    }


    fun getChildMeasure(childId: Int) {
        _childWithMeasureState.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getAllChildWithMeasure()
            response.onSuccess { childrenList ->
                val child = childrenList.firstOrNull() { it.id == childId }

                val lastMeasure = child?.measurements?.maxByOrNull { it.measured_at ?: "" }

                val yearMonth = formatDateToMonthYear(lastMeasure?.measured_at)

                val ageCalulate = calculateAgeFromBirthToMeasure(
                    birth = child?.birth ?: "",
                    measuredAt = lastMeasure?.measured_at ?: ""
                )

                Log.d("ChildViewModel", "Child: $child")
                Log.d("ChildViewModel", "Last Measure: $lastMeasure")
                Log.d("ChildViewModel", "Age Result: $ageCalulate")



                if (child != null && lastMeasure != null) {
                    _childWithMeasureState.value = UiState.Success(
                        ChildWithMeasure(
                            children_id = child.id,
                            name = child.name,
                            id = lastMeasure.id,
                            birth = child.birth,
                            measured_at = lastMeasure.measured_at,
                            ageResult = ageCalulate,
                            year = yearMonth?.first,
                            month = yearMonth?.second,
                            heightCm = lastMeasure.heightCm,
                            weightKg = lastMeasure.weightKg,
                            armCm = lastMeasure.armCm,
                            headCm = lastMeasure.headCm,
                        )
                    )
                } else {
                    _childWithMeasureState.value = UiState.Success(
                        defaultChildMeasure()
                    )
                    Log.d("ChildViewModel", "Child or last measure is null")
                }
            }
        }
    }

    fun getAllChildMeasure() {
        _allChilWithMeasuredState.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getAllChildWithMeasure()
            response.onSuccess {
                val allMeasure = it.flatMap { children ->
                    children.measurements?.map { measure ->
                        val ageResult = calculateAgeFromBirthToMeasure(
                            birth = children.birth ?: "",
                            measuredAt = measure.measured_at ?: ""
                        )
                        ChildWithMeasure(
                            children_id = children.id,
                            name = children.name,
                            id = measure.id,
                            birth = children.birth,
                            measured_at = measure.measured_at,
                            ageResult = ageResult,
                            heightCm = measure.heightCm,
                            weightKg = measure.weightKg,
                            armCm = measure.armCm,
                            headCm = measure.headCm
                        )
                    } ?: emptyList()
                }.sortedByDescending { it.measured_at }
                if (allMeasure.isEmpty()) {
                    _allChilWithMeasuredState.value = UiState.Empty
                } else {
                    _allChilWithMeasuredState.value = UiState.Success(allMeasure)
                }
                Log.d("UserViewModel", "Get All Children with measure Sukses: $allMeasure")
            }.onFailure {
                _allChilWithMeasuredState.value = UiState.Error("Gagal mendapatkan data")
                Log.d("UserViewModel", "Get All Children with measure Gagal: $it")
            }
        }
    }

    fun defaultSelectUser(): User {
        return User(
            id_users = "",
            name = "",
            birth = "",
            phone = "",
            children = emptyList()
        )
    }

    fun selectUserWithChild(userId: String?) {
        _newMeasureState.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.getAllUsersWithChild()
            response.onSuccess { userList ->
                val user = userList.firstOrNull() { it.id_users == userId }

                if (user == null) {
                    _newMeasureState.value =
                        UiState.Error("User tidak ditemukan")
                    return@launch
                }

                val mapUser = User(
                    id_users = user.id_users,
                    name = user.name,
                    birth = user.birth,
                    phone = user.phone,
                    children = user.children?.map { child ->
                        Children(
                            id = child.id,
                            name = child.name,
                            ageResult = calculateAgeFromBirthToNow(
                                birth = child.birth ?: ""
                            )
                        )
                    }
                )
                _newMeasureState.value = UiState.Success(mapUser)
                Log.d("UserViewModel", "Get all users with child sukses: $mapUser")

            }.onFailure {
                _newMeasureState.value = UiState.Error("Gagal mendapatkan data")
                Log.d("UserViewModel", "Get all users with child GAGAL: $it")
            }
        }
    }

    fun insertNewMeasure(measure: NewMeasure) {
        if (
            measure.heightCm == null ||
            measure.weightKg == null ||
            measure.armCm == null ||
            measure.headCm == null
        ) {
            _insertNewMeasureState.value =
                UiState.Error("Data pengukuran tidak lengkap")
            return
        }
        Log.d("ChildViewModel", "Insert new measure: $measure")
        _insertNewMeasureState.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.insertNewMeasureChild(measure)
            response.onSuccess {
                _insertNewMeasureState.value = UiState.Success("Insert new measure sukses")
                Log.d("ChildViewModel", "Insert new measure sukses: $it")
            }.onFailure {
                _insertNewMeasureState.value = UiState.Error("Gagal insert data")
                Log.d("ChildViewModel", "Insert new measure GAGAL: $it")
            }
        }
    }

    fun insertNewChildUser(child: InsertChildren) {
        _insertNewChildState.value = UiState.Loading
        viewModelScope.launch {
            val newChild = InsertChildren(
                users_id = child.users_id,
                name = child.name,
                nik = child.nik,
                gender = child.gender,
                birth = child.birth,
                heightCm = child.heightCm,
                weightKg = child.weightKg,
                armCm = child.armCm,
                headCm = child.headCm
            )
            val response = userService.insertNewChildUser(newChild)
            response.onSuccess {
                _insertNewChildState.value = UiState.Success("Insert new child sukses")
                Log.d("ChildViewModel", "Insert new child sukses: $it")
            }.onFailure {
                _insertNewChildState.value = UiState.Error("Gagal insert data")
                Log.d("ChildViewModel", "Insert new child GAGAL: $it")
            }
        }
    }


    fun updateChild(child: Children) {
        _childId.value = UiState.Loading
        viewModelScope.launch {
            val response = userService.updateChild(child)
            response.onSuccess {
                _childId.value = UiState.Success(it)
                Log.d("ChildViewModel", "Update profile children sukses: $it")
            }.onFailure {
                _childId.value = UiState.Error("Gagal update profil")
                Log.d("ChildViewModel", "Update profil children GAGAL: $it")
            }
        }
    }


}