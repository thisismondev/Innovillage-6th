package id.co.mondo.ukhuwah.data.supabase

import android.util.Log
import id.co.mondo.ukhuwah.data.model.User
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from

class UserService {

    private val supabase = SupabaseConfig.client


    suspend fun createUser(user: User): Result<User> {
        return try {
            val authUserId = supabase.auth.currentUserOrNull()?.id ?: return Result.failure(
                Exception("Auth user tidak ditemukan")
            )
            Log.d("UserService", "Auth user ditemukan: $authUserId")

            val profile = user.copy(id_users = authUserId)
            Log.d("UserService", "Profile siap disimpan: $profile")

            supabase.from("users").insert(profile)
            Log.d("UserService", "Insert profile sukses")
            Result.success(profile)
        } catch (e: Exception) {
            Log.e("UserService", "Insert profile GAGAL", e)
            Result.failure(e)

        }

    }


}