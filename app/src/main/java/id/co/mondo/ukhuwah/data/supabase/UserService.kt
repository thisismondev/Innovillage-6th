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

            val email = supabase.auth.currentUserOrNull()?.email ?: return Result.failure(
                Exception("Email user tidak ditemukan")
            )
            Log.d("UserService", "Email user ditemukan: $email")

            val profile = user.copy(id_users = authUserId, email = email)
            Log.d("UserService", "Profile siap disimpan: $profile")

            supabase.from("users").insert(profile)
            Log.d("UserService", "Insert profile sukses")
            Result.success(profile)
        } catch (e: Exception) {
            Log.e("UserService", "Insert profile GAGAL", e)
            Result.failure(e)

        }

    }

    suspend fun getAllUsers(): Result<List<User>> {
        return try {
            val users = supabase
                .from("users")
                .select {
                    filter {
                        eq("role", "Parent")
                    }
                }
                .decodeList<User>()
            Log.d("UserService", "Get all users sukses: $users")
            Result.success(users)
        } catch (e: Exception) {
            Log.e("UserService", "Get all users GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun getUserById(id: String): Result<User> {
        return try {
            val users = supabase
                .from("users")
                .select {
                    filter {
                        eq("id_users", id)
                    }
                }
                .decodeSingle<User>()
            Log.d("UserService", "Get all users sukses: $users")
            Result.success(users)
        } catch (e: Exception) {
            Log.e("UserService", "Get all users GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun updateUser(user: User): Result<User> {

        if (user.id_users == null) {
            return Result.failure(
                Exception("id_users tidak ditemukan")
            )
        }

        return try {
            val user = supabase
                .from("users")
                .update(
                    {
                        user.name?.let { set("name", it) }
                        user.nik?.let { set("nik", it) }
                        user.gender?.let { set("gender", it) }
                        user.birth?.let { set("birth", it) }
                        user.phone?.let { set("phone", it) }
                        user.address?.let { set("address", it) }
                    }
                ){
                    select()
                    filter {
                        eq("id_users", user.id_users)
                    }
                }
                .decodeSingle<User>()
            Log.d("UserService", "Get all users sukses: $user")
            Result.success(user)
        } catch (e: Exception) {
            Log.e("UserService", "Get all users GAGAL", e)
            Result.failure(e)
        }
    }
}