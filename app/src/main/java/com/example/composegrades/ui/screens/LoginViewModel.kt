package com.example.composegrades.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LoginState(
    val email: String = "",
    val password: String = "",
    val emailError: String? = null,
    val passwordError: String? = null
)

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun onEmailChange(v: String) {
        state = state.copy(email = v, emailError = null)
    }

    fun onPasswordChange(v: String) {
        state = state.copy(password = v, passwordError = null)
    }

    private fun isEmailValid(email: String): Boolean =
        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

    fun canLogin(): Boolean {
        var emailErr: String? = null
        var passErr: String? = null

        if (state.email.isBlank()) emailErr = "Ingresa tu correo."
        else if (!isEmailValid(state.email)) emailErr = "Formato de correo inválido."

        if (state.password.isBlank()) passErr = "Ingresa tu contraseña."

        state = state.copy(emailError = emailErr, passwordError = passErr)
        return emailErr == null && passErr == null
    }

    fun username(): String = state.email.substringBefore("@").ifBlank { "Usuario" }
}