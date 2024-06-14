package com.afebrii.jogjaventure.ui.screen

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.navigation.NavController
import com.afebrii.jogjaventure.ui.firebase.FirebaseHelper

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onLoginClick: () -> Unit,
    navController: NavController? = null // Tambahkan parameter NavController
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
                    submitRegistration(email, password, onRegisterSuccess, context, navController)
                }
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            keyboardController?.hide()
            submitRegistration(email, password, onRegisterSuccess, context, navController)
        }) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onLoginClick) {
            Text("Already have an account? Login here")
        }
    }
}

private fun submitRegistration(
    email: String,
    password: String,
    onRegisterSuccess: () -> Unit,
    context: Context,
    navController: NavController?
) {
    FirebaseHelper.submitRegistration(email, password) { isSuccessful ->
        if (isSuccessful as Boolean) {
            onRegisterSuccess()
            navController?.navigate("wisata_screen")
        } else {
            Toast.makeText(context, "Registration failed", Toast.LENGTH_SHORT).show()
        }
    }
}
@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(
        onRegisterSuccess = {},
        onLoginClick = {}
    )
}