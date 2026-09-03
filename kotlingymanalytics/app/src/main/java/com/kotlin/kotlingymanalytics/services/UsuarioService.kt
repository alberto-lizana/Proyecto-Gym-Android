package com.kotlin.kotlingymanalytics.services

import java.time.LocalDate
import java.time.Period

class UsuarioService {

    fun calcular(fechaNacimiento: LocalDate): Int {
        return Period.between(
            fechaNacimiento,
            LocalDate.now()
        ).years
    }
}