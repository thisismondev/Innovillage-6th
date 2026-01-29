package id.co.mondo.ukhuwah.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class UserExport(
    val id_users: String? = null,
    val name: String? = null,
    val nik: String? = null,
    val phone: String? = null,
    val address: String? = null,
    val role: String? = null
)

@Serializable
data class ChildExport(
    val id: Int? = null,
    val users_id: String? = null,
    val name: String? = null,
    val nik: String? = null,
    val gender: String? = null,
    val birth: String? = null,
    val users: UserExport? = null,
    val measurements: List<MeasurementExport>? = emptyList()
)

@Serializable
data class MeasurementExport(
    val id: Int? = null,
    val children_id: Int? = null,
    val measured_at: String? = null,
    val weightKg: Double? = null,
    val heightCm: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
    val status: String? = null
)


@Serializable
data class BalitaData(
    @SerializedName("nik")
    val nik: String,
    @SerializedName("namaAnak")
    val namaAnak: String,
    @SerializedName("tglLahir")
    val tglLahir: String,
    @SerializedName("umur")
    val umur: String?,

    // Field Optional (Boleh kosong, kita kasih default value)
    @SerializedName("jk")
    val jk: String = "",
    @SerializedName("namaOrtu")
    val namaOrtu: String = "",
    @SerializedName("nikOrtu")
    val nikOrtu: String = "",
    @SerializedName("hp")
    val hp: String = "",
    @SerializedName("alamat")
    val alamat: String = "",

    // Pengukuran (Default 0.0)
    @SerializedName("bulan")
    val bulan: String = "",
    @SerializedName("bb")
    val bb: Double? = null,
    @SerializedName("tb")
    val tb: Double? = null,
    @SerializedName("lila")
    val lila: Double? = null,
    @SerializedName("lkep")
    val lkep: Double? = null,
    @SerializedName("status")
    val status: String? = null
)

@Serializable
data class DataResponse(
    val success: Boolean,
    val message: String,
    val total: Int,
    val processed: Int
)
