package com.afebrii.jogjaventure.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import com.afebrii.jogjaventure.ui.data.Screen
import com.afebrii.jogjaventure.ui.screen.LoginScreen
import com.afebrii.jogjaventure.ui.screen.RegisterScreen
import com.afebrii.jogjaventure.ui.screen.ScreenDetail
import com.afebrii.jogjaventure.ui.screen.WisataScreen

@Composable
fun AppNavigation(navController: NavHostController, currentScreen: Screen) {
    when (currentScreen) {
        is Screen.Login -> LoginScreen(
            onLoginSuccess = { navController.navigate(Screen.Wisata.route) },
            onRegisterClick = { navController.navigate(Screen.Register.route) },
            navController = navController
        )
        is Screen.Register -> RegisterScreen(
            onRegisterSuccess = { navController.navigate(Screen.Wisata.route) },
            onLoginClick = { navController.popBackStack() },
            navController = navController
        )
        is Screen.Wisata -> WisataScreen(navController = navController) { placeName ->
            // Navigasi ke Screen.Detail saat item di WisataScreen diklik
            navController.navigate("detail_screen/$placeName")
        }
        is Screen.Detail -> {
            // Panggil ScreenDetail saat ada navigasi ke Screen.Detail
            ScreenDetail(placeName = currentScreen.placeName) {
                navController.popBackStack()
            }
        }    }
}

