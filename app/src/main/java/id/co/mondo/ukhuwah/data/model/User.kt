package id.co.mondo.ukhuwah.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id_users: String? = null,
    val name: String? = null,
    val email: String? = null,
    val nik: String? = null,
    val gender: String? =  null,
    val birth: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val role: String? = null,
    val created_at: String? = null,
    val children: List<Children>? = null
)
