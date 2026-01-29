package id.co.mondo.ukhuwah.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NewMeasure(
    val children_id: Int? = null,
    val measured_at: String? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
)


@Serializable
data class Measurements(
    val id: Int? = null,
    val children_id: Int? = null,
    val measured_at: String? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
    val created_at: String? = null
)