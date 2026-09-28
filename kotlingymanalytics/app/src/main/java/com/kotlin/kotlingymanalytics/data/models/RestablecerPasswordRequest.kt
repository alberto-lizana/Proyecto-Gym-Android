package com.kotlin.kotlingymanalytics.data.models

data class RestablecerPasswordRequest(
    val email: String,
    val codigo: String,
    val nuevaPassword: String
)