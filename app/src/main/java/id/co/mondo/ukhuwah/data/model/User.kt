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

@Serializable
data class ListChild(
    val id: Int? = null,
    val nameChild: String? = null,
    val ageResult: AgeResult? = null
)


@Serializable
data class InsertUser(
    val id_users: String? = null,
    val name: String? = null,
    val email: String? = null,
    val nik: String? = null,
    val gender: String? =  null,
    val birth: String? = null,
    val phone: String? = null,
    val address: String? = null,
)

@Serializable
data class RoleUser(
    val id_users: String? = null,
    val name: String? = null,
    val role: String,
)