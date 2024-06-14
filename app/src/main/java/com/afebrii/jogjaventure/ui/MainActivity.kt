package com.afebrii.jogjaventure.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.rememberNavController
import com.afebrii.jogjaventure.ui.data.Screen
import com.afebrii.jogjaventure.ui.navigation.AppNavigation
import com.afebrii.jogjaventure.ui.screen.MyApp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}