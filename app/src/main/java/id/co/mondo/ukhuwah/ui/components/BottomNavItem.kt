package id.co.mondo.ukhuwah.ui.components

import id.co.mondo.ukhuwah.R

enum class BottomNavItem(
    val route: String,
    val icon: Int,
) {
    HOME(
        route = "home",
        icon = R.drawable.home
    ),
    DETAIL(
        route = "detail",
        icon = R.drawable.detail
    ),
    ADD(
        route = "update-children",
        icon = R.drawable.add
    ),
    HISTORY(
        route = "history",
        icon = R.drawable.history,
    ),
    PARENT(
        route = "parent",
        icon = R.drawable.parent,
    )
}