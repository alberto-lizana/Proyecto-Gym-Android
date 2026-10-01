package com.kotlin.kotlingymanalytics.remote.dto

data class RestablecerPasswordRequest(
    val email: String,
    val codigo: String,
    val nuevaPassword: String
)