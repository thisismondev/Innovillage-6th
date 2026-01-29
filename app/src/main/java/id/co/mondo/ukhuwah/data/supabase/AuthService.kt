package id.co.mondo.ukhuwah.data.supabase

import android.util.Log
import id.co.mondo.ukhuwah.data.model.Account
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email

class AuthService {

    private val supabase = SupabaseConfig.client

    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            supabase.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            Log.d("AuthService", "$email Login berhasil")
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }


    }


    suspend fun restoreSession(): Boolean {
        return try {
            supabase.auth.loadFromStorage()
            val current = supabase.auth.currentSessionOrNull()
            Log.d("AuthService", "current session = ${current?.accessToken}")
            current != null
        } catch (e: Exception) {
            Log.d("AuthService", "error = $e")
            false
        }
    }

    suspend fun register(
        email: String,
        password: String,
    ): Result<Account> {
        return try {
            supabase.auth.signUpWith(Email) {
                this.email = email.trim()
                this.password = password
            }

            val authUserId = supabase.auth.currentUserOrNull()?.id ?: return Result.failure(
                Exception("Auth user tidak ditemukan")
            )
            Log.d("UserService", "id user ditemukan: $authUserId")

            val emailUser = supabase.auth.currentUserOrNull()?.email ?: return Result.failure(
                Exception("Email user tidak ditemukan")
            )
            Log.d("UserService", "Email user ditemukan: $emailUser")

            Result.success(
                Account(
                    id = authUserId,
                    email = emailUser
                )
            )
        } catch (e: Exception) {
            val message = when {
                e.message?.contains("already registered", true) == true ->
                    "Email sudah terdaftar"

                else ->
                    "Gagal membuat akun"
            }
            Log.d("UserService", "Gagal membuat akun: $message")
            Result.failure(Exception(message))
        }
    }

    suspend fun logout(): Result<Unit> {
        return try {
            supabase.auth.signOut()
            Log.d("AuthService", "Logout berhasil")
            val current = supabase.auth.currentSessionOrNull()
            Log.d("AuthService", "current session = $current")
            supabase.auth.clearSession()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteUser(userId: String): Result<Unit> {
        return try {
            supabase.auth.admin.deleteUser(userId)
            Log.d("AuthService", "Auth user berhasil dihapus: $userId")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("AuthService", "Gagal hapus auth user", e)
            Result.failure(e)
        }
    }

    suspend fun updateUser(email: String): Result<Unit> {
        return try {
            supabase.auth.updateUser {
                this.email = email
            }
            Log.d("AuthService", "Update user berhasil")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(
                "AuthService",
                "Update user GAGAL: ${e.message}",
                e
            )
            Result.failure(e)
        }
    }

}