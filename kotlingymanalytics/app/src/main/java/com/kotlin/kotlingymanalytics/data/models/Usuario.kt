package com.kotlin.kotlingymanalytics.data.models
import java.time.LocalDate
import java.time.Period

data class Usuario (
    val nombre: String,
    val appat: String,
    val apmat: String?,
    val fechaNacimiento: LocalDate,
    val email: String,
    val password: String
) {

    val edad: Int
        get() = calcularEdad()

    private fun calcularEdad(): Int {
        return Period.between(
            fechaNacimiento,
            LocalDate.now()
        ).years
    }

}