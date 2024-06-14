package com.afebrii.jogjaventure.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afebrii.jogjaventure.ui.data.Screen

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Login.route) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Screen.Wisata.route) },
                onRegisterClick = { navController.navigate(Screen.Register.route) },
                navController = navController
            )
        }
        composable(Screen.Register.route) {
            RegisterScreen(
                onRegisterSuccess = { navController.navigate(Screen.Wisata.route) },
                onLoginClick = { navController.popBackStack() },
                navController = navController
            )
        }
        composable(Screen.Wisata.route) {
            WisataScreen(navController = navController) { placeName ->
                // Navigasi ke Screen.Detail saat item di WisataScreen diklik
                navController.navigate("detail_screen/$placeName")
            }
        }
        composable("detail_screen/{placeName}") { backStackEntry ->
            val placeName = backStackEntry.arguments?.getString("placeName")
            if (placeName != null) {
                ScreenDetail(placeName = placeName) {
                    navController.popBackStack()
                }
            }
        }
    }
}

