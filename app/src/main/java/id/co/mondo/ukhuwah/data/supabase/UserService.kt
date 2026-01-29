package id.co.mondo.ukhuwah.data.supabase

import android.util.Log
import id.co.mondo.ukhuwah.data.model.ChildExport
import id.co.mondo.ukhuwah.data.model.Children
import id.co.mondo.ukhuwah.data.model.InsertChildren
import id.co.mondo.ukhuwah.data.model.InsertUser
import id.co.mondo.ukhuwah.data.model.NewMeasure
import id.co.mondo.ukhuwah.data.model.RoleUser
import id.co.mondo.ukhuwah.data.model.User
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns

class UserService {

    private val supabase = SupabaseConfig.client


    suspend fun createUser(user: InsertUser): Result<String> {
        return try {
            val result = supabase.from("users").insert(user)
            Log.d("UserService", "Insert profile sukses: $result")
            Result.success("Berhasil membuat User")
        } catch (e: Exception) {
            Log.e("UserService", "Insert profile GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun getUserRole(): Result<RoleUser> {
        return try {
            val userId = supabase.auth.currentUserOrNull()?.id
                ?: return Result.failure(Exception("User belum login"))
            val select = Columns.raw(
                """
                    id_users,
                    name,
                    role
                """.trimIndent()
            )

            val roleUser = supabase
                .from("users")
                .select(select) {
                    filter {
                        eq("id_users", userId)
                    }
                }
                .decodeSingle<RoleUser>()

            Log.d("AuthService", "Role user = $roleUser")

            Result.success(roleUser)

        } catch (e: Exception) {
            Log.e("AuthService", "Gagal ambil role", e)
            Result.failure(e)
        }
    }

    suspend fun exportUsers(): Result<List<ChildExport>> {
        return try {
            val select = Columns.raw(
                """
                id,
                users_id,
                name,
                nik,
                gender,
                birth,
                users(
                   id_users,
                   name,
                   nik,
                   phone,
                   address,
                   role
                ),
                measurements(
                    id,
                    children_id,
                    measured_at,
                    heightCm,
                    weightKg,
                    armCm,
                    headCm
                )
                """.trimIndent()
            )
            val child = supabase
                .from("children")
                .select(select)
                .decodeList<ChildExport>()
                .filter {
                    (it.users?.role == "Parent")
                }

            Log.d("UserService", "Export Select Child sukses: $child")
            Result.success(child)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getAllUsers(): Result<List<User>> {
        return try {
            val select = Columns.raw(
                """
                id_users,
                name,
                nik,
                gender,
                birth,
                phone,
                address,
                role,
                email,
                created_at,
                children(
                    id
                )
                """.trimIndent()
            )
            val users = supabase
                .from("users")
                .select(select) {
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

    suspend fun getAllUsersWithChild(): Result<List<User>> {
        return try {
            val select = Columns.raw(
                """
                id_users,
                name,
                birth,
                phone,
                role,
                children(
                   id,
                    name,
                    birth
                )
                """.trimIndent()
            )
            val users = supabase
                .from("users")
                .select(select) {
                    filter {
                        eq("role", "Parent")
                    }
                }
                .decodeList<User>()
            Log.d("UserService", "Get all users sukses: $users")
            Result.success(users)
        } catch (e: Exception) {
            Log.e("UserService", "Get all users with child GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun getUserById(id: String): Result<User> {
        return try {
            val select = Columns.raw(
                """
                id_users,
                name,
                nik,
                gender,
                birth,
                phone,
                address,
                role,
                email,
                created_at,
                children(
                    id,
                    users_id,
                    name,
                    birth,
                    created_at
                )
                """.trimIndent()
            )
            val users = supabase
                .from("users")
                .select(select) {
                    filter {
                        eq("id_users", id)
                    }
                }
                .decodeSingle<User>()
            Log.d("UserService", "Get user By Id sukses: $users")
            Result.success(users)
        } catch (e: Exception) {
            Log.e("UserService", "Get user By Id GAGAL", e)
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
                ) {
                    select()
                    filter {
                        eq("id_users", user.id_users)
                    }
                }
                .decodeSingle<User>()
            Log.d("UserService", "Update user sukses: $user")
            Result.success(user)
        } catch (e: Exception) {
            Log.e("UserService", "Update user GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun getChildById(id: Int): Result<Children> {
        return try {
            val select = Columns.raw(
                """
                id,
                users_id,
                name,
                nik,
                gender,
                birth,
                heightCm,
                weightKg,
                armCm,
                headCm,
                created_at,
                measurements(
                    id,
                    children_id,
                    measured_at,
                    heightCm,
                    weightKg,
                    armCm,
                    headCm
                )
                """.trimIndent()
            )
            val child = supabase
                .from("children")
                .select(select) {
                    filter {
                        eq("id", id)
                    }
                }
                .decodeSingle<Children>()
            Log.d("UserService", "Get Child By Id sukses: $child")
            Result.success(child)
        } catch (e: Exception) {
            Log.e("UserService", "Get Child  By Id GAGAL", e)
            Result.failure(e)
        }

    }

    suspend fun updateChild(child: Children): Result<Children> {

        if (child.id == null) {
            return Result.failure(
                Exception("id tidak ditemukan")
            )
        }

        return try {
            val child = supabase
                .from("children")
                .update(
                    {
                        child.name?.let { set("name", it) }
                        child.nik?.let { set("nik", it) }
                        child.gender?.let { set("gender", it) }
                        child.birth?.let { set("birth", it) }
                        child.heightCm?.let { set("heightCm", it) }
                        child.weightKg?.let { set("weightKg", it) }
                        child.armCm?.let { set("armCm", it) }
                        child.headCm?.let { set("headCm", it) }
                    }
                ) {
                    select()
                    filter {
                        eq("id", child.id)
                    }
                }
                .decodeSingle<Children>()
            Log.d("UserService", "Update child sukses: $child")
            Result.success(child)
        } catch (e: Exception) {
            Log.e("UserService", "Update child GAGAL", e)
            Result.failure(e)
        }
    }


    suspend fun getAllChildWithMeasure(): Result<List<Children>> {
        return try {
            val select = Columns.raw(
                """
                id,
                users_id,
                name,
                nik,
                gender,
                birth,
                heightCm,
                weightKg,
                armCm,
                headCm,
                created_at,
                measurements(
                    id,
                    children_id,
                    measured_at,
                    heightCm,
                    weightKg,
                    armCm,
                    headCm
                )
                """.trimIndent()
            )
            val child = supabase
                .from("children")
                .select(select)
                .decodeList<Children>()
            Log.d("UserService", "Get all children & Measure sukses: $child")
            Result.success(child)
        } catch (e: Exception) {
            Log.e("UserService", "Get all children & Measure GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun insertNewMeasureChild(
        measure: NewMeasure
    ): Result<Unit> {
        return try {
            val result = supabase
                .from("measurements")
                .insert(measure)
            Log.d("UserService", "Insert new Measure sukses: $result")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("UserService", "Insert new Measure GAGAL", e)
            Result.failure(e)
        }
    }

    suspend fun insertNewChildUser(child: InsertChildren): Result<Unit> {
        return try {
            val result = supabase
                .from("children")
                .insert(child)
            Log.d("UserService", "Insert new child sukses: $result")
            Result.success(Unit)
        } catch (e: Exception) {
            Log.e("UserService", "Insert new child GAGAL", e)
            Result.failure(e)
        }


    }

}