package id.co.mondo.ukhuwah.data.supabase

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseConfig {
    val client = createSupabaseClient(
        supabaseUrl ="https://zkxrfgbospnavtodhsjv.supabase.co",
//            BuildConfig.SUPABASE_URL,
        supabaseKey = "sb_publishable_uI6--LFWncwLdlbgIz_hQQ_ShJT7nE_"
//            BuildConfig.SUPABASE_KEY
    ) {
        install(Auth){
            autoLoadFromStorage = true
            autoSaveToStorage = true
        }
        install(Postgrest)
        install(Storage)
    }
}