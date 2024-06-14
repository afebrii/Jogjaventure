package com.afebrii.jogjaventure.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.afebrii.jogjaventure.ui.data.Screen
import com.afebrii.jogjaventure.ui.firebase.FirebaseHelper

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    onRegisterClick: () -> Unit,
    navController: NavHostController // Tambahkan parameter navController di sini
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { /* Handle next action */ }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    if (email.isNotBlank() && password.isNotBlank()) { // Tambahkan validasi di sini
                        submitLogin(email, password, onLoginSuccess, context, navController)
                    } else {
                        Toast.makeText(context, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol Login
        Button(
            onClick = {
                keyboardController?.hide()
                if (email.isNotBlank() && password.isNotBlank()) { // Tambahkan validasi di sini
                    submitLogin(email, password, onLoginSuccess, context, navController)
                } else {
                    Toast.makeText(context, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = email.isNotBlank() && password.isNotBlank() // Setel enable menjadi false jika email atau password kosong
        ) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tombol untuk ke layar pendaftaran jika belum memiliki akun
        TextButton(onClick = onRegisterClick) {
            Text("Don't have an account? Register here")
        }
    }
}


private fun submitLogin(
    email: String,
    password: String,
    onLoginSuccess: () -> Unit,
    context: Context,
    navController: NavHostController // Tambahkan parameter navController di sini
) {
    FirebaseHelper.submitLogin(email, password) { isSuccessful ->
        if (isSuccessful as Boolean) {
            onLoginSuccess()
            navController.navigate(Screen.Wisata.route)
        } else {
            Toast.makeText(context, "Login failed", Toast.LENGTH_SHORT).show()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        onLoginSuccess = {},
        onRegisterClick = {},
        navController = rememberNavController() // Ini hanya untuk pratinjau, pastikan untuk menginisialisasi NavHostController saat digunakan di MainActivity
    )
}

