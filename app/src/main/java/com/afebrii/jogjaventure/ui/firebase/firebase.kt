package com.afebrii.jogjaventure.ui.firebase

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

data class User(val email: String, val password: String)

object FirebaseHelper {
    fun submitLogin(email: String, password: String, onLoginResult: (Boolean) -> Unit) {
        val loginSuccessful = true
        onLoginResult(loginSuccessful)
    }

    fun submitRegistration(email: String, password: String, onRegisterResult: (Boolean) -> Unit) {
        val database = Firebase.database
        val usersRef = database.getReference("users")
        val userId = usersRef.push().key ?: ""
        val user = User(email, password)
        usersRef.child(userId).setValue(user)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onRegisterResult(true)
                } else {
                    onRegisterResult(false)
                }
            }
    }
}
