package com.kotlin.kotlingymanalytics.remote.dto

data class UsuarioResponse(
    val id: Long,
    val nombre: String,
    val appat: String,
    val apmat: String,
    val fechaNacimiento: String,
    val email: String,
    val nombreRol: String,
    val sexo: String
)