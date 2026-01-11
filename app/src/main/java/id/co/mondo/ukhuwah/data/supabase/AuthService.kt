package id.co.mondo.ukhuwah.data.supabase

import android.util.Log
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
            Log.d("AuthService", "current session = $current")
            current != null
        } catch (e: Exception) {
            Log.d("AuthService", "error = $e")
            false
        }
    }

    suspend fun register(
        email: String,
        password: String,
    ): Result<Unit> {
        return try {
            supabase.auth.signUpWith(Email) {
                this.email = email.trim()
                this.password = password
            }
            Log.d("AuthService", "Auth signUp sukses")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e(
                "AuthService",
                "Register GAGAL: ${e.message}",
                e   // ini penting agar stacktrace muncul
            )
            Result.failure(e)
        }
    }

    suspend fun logout(): Result<Unit> {
        return try {
            supabase.auth.signOut()
            Log.d("AuthService", "Logout berhasil")
            val current = supabase.auth.currentSessionOrNull()
            Log.d("AuthService", "current session = $current")
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}