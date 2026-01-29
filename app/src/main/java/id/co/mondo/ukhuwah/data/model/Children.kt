package id.co.mondo.ukhuwah.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Children(
    val id: Int? = null,
    val users_id: String? = null,
    val name: String? = null,
    val nik: String? = null,
    val gender: String? =  null,
    val birth: String? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
    val created_at: String? = null,
    val measurements: List<Measurements>? = null,
    val ageResult: AgeResult? = null
)

@Serializable
data class InsertChildren(
    val users_id: String? = null,
    val name: String? = null,
    val nik: String? = null,
    val gender: String? =  null,
    val birth: String? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
)

@Serializable
data class CountChildMeasure(
    val countChild: Int? = null,
    val countMeasure: Int? = null
)




@Serializable
data class ChildWithMeasure(
    val children_id: Int? = null,
    val name: String? = null,
    val id: Int? = null,
    val birth: String? = null,
    val measured_at: String? = null,
    val ageResult: AgeResult? = null,
    val year: String? = null,
    val month: String? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
)

