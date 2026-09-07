package com.kotlin.kotlingymanalytics.data.session

import com.kotlin.kotlingymanalytics.data.models.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SessionManager {
    private val usuarioConectado = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = usuarioConectado.asStateFlow()

    fun iniciarSesion(usuario: Usuario) {
        usuarioConectado.value = usuario
    }

    fun cerrarSesion() {
        usuarioConectado.value = null
    }
}