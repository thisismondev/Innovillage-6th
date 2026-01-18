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
    val measurements: List<Measurements>? = null
)

@Serializable
data class Measurements(
    val id: Int? = null,
    val children_id: Int? = null,
    val age_days: Int? = null,
    val measured_at: String? = null,
    val heightCm: Double? = null,
    val weightKg: Double? = null,
    val armCm: Double? = null,
    val headCm: Double? = null,
    val created_at: String? = null
){
    val birthFormatted: String
        get() {
            if (measured_at.isNullOrEmpty()) return "-"

            val parts = measured_at.split("-")
            val year = parts[0]
            val month = parts[1]
            val day = parts[2]

            val monthName = when (month) {
                "01" -> "Jan"
                "02" -> "Feb"
                "03" -> "Mar"
                "04" -> "Apr"
                "05" -> "Mei"
                "06" -> "Jun"
                "07" -> "Jul"
                "08" -> "Agu"
                "09" -> "Sep"
                "10" -> "Okt"
                "11" -> "Nov"
                "12" -> "Des"
                else -> "-"
            }

            return "$day $monthName $year"
        }
}


@Serializable
data class MeasureWithChild(
    val id: Int? = null,
    val name: String? = null,
    val measurements: Measurements? = null
)