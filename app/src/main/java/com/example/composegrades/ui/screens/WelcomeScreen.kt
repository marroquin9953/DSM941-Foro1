package com.example.composegrades.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeScreen(
    username: String,
    onContinue: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("¡Bienvenido, $username!", style = MaterialTheme.typography.headlineSmall)
            Button(onClick = onContinue, modifier = Modifier.fillMaxWidth()) {
                Text("Continuar al módulo de notas")
            }
        }
    }
}