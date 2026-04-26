package com.example.composegrades.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class GradesState(
    val n1: String = "",
    val n2: String = "",
    val n3: String = "",
    val error: String? = null
)

class GradesViewModel : ViewModel() {

    var state by mutableStateOf(GradesState())
        private set

    fun onNoteChange(index: Int, value: String) {
        val v = value.replace(",", ".")
        state = when (index) {
            1 -> state.copy(n1 = v, error = null)
            2 -> state.copy(n2 = v, error = null)
            else -> state.copy(n3 = v, error = null)
        }
    }

    private fun toValidFloatOrNull(s: String): Float? =
        s.toFloatOrNull()?.takeIf { it in 0f..10f }

    fun calculateIfValid(): Float? {
        val a = toValidFloatOrNull(state.n1)
        val b = toValidFloatOrNull(state.n2)
        val c = toValidFloatOrNull(state.n3)

        return if (a == null || b == null || c == null) {
            state = state.copy(error = "Ingresa tres números válidos entre 0 y 10.")
            null
        } else {
            (a + b + c) / 3f
        }
    }

    fun clearNotes() {
        state = GradesState()
    }
}