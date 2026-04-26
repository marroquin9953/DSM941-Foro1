package com.example.composegrades.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    state: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ingresar", style = MaterialTheme.typography.headlineMedium)
            OutlinedTextField(
                value = state.email, onValueChange = onEmailChange,
                label = { Text("Correo electrónico") },
                isError = state.emailError != null,
                supportingText = { state.emailError?.let { Text(it) } },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = state.password, onValueChange = onPasswordChange,
                label = { Text("Contraseña") },
                isError = state.passwordError != null,
                supportingText = { state.passwordError?.let { Text(it) } },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation()
            )
            Button(onClick = onLogin, modifier = Modifier.fillMaxWidth()) {
                Text("Iniciar sesión")
            }
        }
    }
}