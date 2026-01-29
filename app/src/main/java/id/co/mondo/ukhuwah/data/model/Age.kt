package id.co.mondo.ukhuwah.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AgeResult(
    val years: Int,
    val months: Int,
    val days: Int
)