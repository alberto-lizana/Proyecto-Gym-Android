package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.SexoTipo

data class UsuarioRequest(
    val nombre: String,
    val appat: String,
    val apmat: String,
    val fechaNacimiento: String,
    val email: String,
    val password: String,
    val sexo: SexoTipo
)