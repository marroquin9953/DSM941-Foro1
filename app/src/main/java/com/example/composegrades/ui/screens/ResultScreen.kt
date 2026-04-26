package com.example.composegrades.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    username: String,
    average: String,
    onEnterAgain: () -> Unit,
    onLogout: () -> Unit
) {
    val avgVal = average.toFloatOrNull() ?: 0f
    val passed = avgVal >= 6f
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Resultado", style = MaterialTheme.typography.headlineSmall)
            Text("Promedio: $average")
            Text(if (passed) "Aprobado" else "Reprobado")
            Button(onClick = onEnterAgain, modifier = Modifier.fillMaxWidth()) {
                Text("Ingresar otras notas")
            }
            OutlinedButton(onClick = onLogout, modifier = Modifier.fillMaxWidth()) {
                Text("Cerrar sesión")
            }
        }
    }
}