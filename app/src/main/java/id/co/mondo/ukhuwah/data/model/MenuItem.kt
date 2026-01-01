package id.co.mondo.ukhuwah.data.model

import androidx.compose.ui.graphics.Color
import id.co.mondo.ukhuwah.R
import id.co.mondo.ukhuwah.ui.theme.bakunparent
import id.co.mondo.ukhuwah.ui.theme.exanak
import id.co.mondo.ukhuwah.ui.theme.inkes
import id.co.mondo.ukhuwah.ui.theme.jalper
import id.co.mondo.ukhuwah.ui.theme.statper
import id.co.mondo.ukhuwah.ui.theme.tpanak

data class MenuItem(
    val title: String,
    val icon: Int,
    val color: Color,
    val routes: String
)


val menuItems = listOf(
    MenuItem(
        title = "Statistik Pertumbuhan",
        icon = R.drawable.statistik,
        color = statper,
        routes = "detail"
    ),
    MenuItem(
        title = "Perjalanan Pertumbuhan",
        icon = R.drawable.babyfeet,
        color = jalper,
        routes = "detail"
    ),MenuItem(
        title = "Infotmasi Kesehatan",
        icon = R.drawable.openbook,
        color = inkes,
        routes = "news"
    ),MenuItem(
        title = "Tambah Profile Anak",
        icon = R.drawable.children,
        color = tpanak,
        routes = "add-children"
    ),MenuItem(
        title = "Buat Akun Orang Tua",
        icon = R.drawable.family,
        color = bakunparent,
        routes = "register"
    ),
    MenuItem(
        title = "Export Data Anak",
        icon = R.drawable.export,
        color = exanak,
        routes = ""
    ),
)
