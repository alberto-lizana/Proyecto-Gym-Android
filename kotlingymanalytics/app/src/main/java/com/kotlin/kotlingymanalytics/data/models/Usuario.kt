package com.kotlin.kotlingymanalytics.data.models
import java.time.LocalDate

data class Usuario (

    val nombre: String,
    val appat: String,
    val apmat: String,
    val fechaNacimiento: LocalDate,
    val email: String,
    val password: String
)

val usuarios = listOf(
    Usuario(
        nombre = "Alberto",
        appat = "Lizana",
        apmat = "Rojas",
        fechaNacimiento = LocalDate.of(1994, 9, 15),
        email = "alberto@gmail.com",
        password = "123456"
    ),
    Usuario(
        nombre = "Juan",
        appat = "Pérez",
        apmat = "González",
        fechaNacimiento = LocalDate.of(1998, 8, 24),
        email = "juan@gmail.com",
        password = "123456"
    ),
    Usuario(
        nombre = "María",
        appat = "Soto",
        apmat = "Muñoz",
        fechaNacimiento = LocalDate.of(2000, 3, 15),
        email = "maria@gmail.com",
        password = "123456"
    ),
    Usuario(
        nombre = "Camila",
        appat = "Rojas",
        apmat = "Vargas",
        fechaNacimiento = LocalDate.of(1997, 11, 2),
        email = "camila@gmail.com",
        password = "123456"
    ),
    Usuario(
        nombre = "Diego",
        appat = "Contreras",
        apmat = "Silva",
        fechaNacimiento = LocalDate.of(1994, 7, 30),
        email = "diego@gmail.com",
        password = "123456"
    )
)