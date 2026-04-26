package com.example.composegrades.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun GradesScreen(
    state: GradesState,
    onNoteChange: (Int, String) -> Unit,
    onCalculate: () -> Unit,
    onBack: () -> Unit
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ingresar Notas", style = MaterialTheme.typography.headlineSmall)
            OutlinedTextField(
                value = state.n1, onValueChange = { onNoteChange(1, it) },
                label = { Text("Nota 1 (0–10)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = state.n2, onValueChange = { onNoteChange(2, it) },
                label = { Text("Nota 2 (0–10)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = state.n3, onValueChange = { onNoteChange(3, it) },
                label = { Text("Nota 3 (0–10)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            state.error?.let { Text(it, color = MaterialTheme.colorScheme.error) }
            Button(onClick = onCalculate, modifier = Modifier.fillMaxWidth()) {
                Text("Calcular promedio")
            }
            TextButton(onClick = onBack) { Text("Volver") }
        }
    }
}