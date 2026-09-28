package com.kotlin.kotlingymanalytics.data.session

import com.kotlin.kotlingymanalytics.data.models.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object SessionManager {

    private val usuarioConectado = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = usuarioConectado.asStateFlow()

    private var token: String? = null

    fun iniciarSesion(
        usuario: Usuario,
        token: String
    ) {
        usuarioConectado.value = usuario
        this.token = token
    }

    fun obtenerToken(): String? {
        return token
    }

    fun cerrarSesion() {
        usuarioConectado.value = null
        token = null
    }
}