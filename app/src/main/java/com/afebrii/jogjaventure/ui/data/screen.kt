package com.afebrii.jogjaventure.ui.data

sealed class Screen(val route: String) {
    object Login : Screen("login_screen")
    object Register : Screen("register_screen")
    object Wisata : Screen("wisata_screen")
    data class Detail(val placeName: String) : Screen("detail_screen/$placeName")
}
