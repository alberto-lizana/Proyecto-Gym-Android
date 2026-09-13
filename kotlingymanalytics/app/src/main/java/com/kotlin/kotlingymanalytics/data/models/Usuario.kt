package com.kotlin.kotlingymanalytics.data.models
import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import java.time.LocalDate
import java.time.Period

class Usuario(
    // id: Long,
    nombre: String,
    appat: String,
    apmat: String?,
    fechaNacimiento: LocalDate,
    email: String,
    password: String,
    sexo: SexoTipo
) {
    var nombre = nombre
        private set

    var appat = appat
        private set

    var apmat = apmat
        private set

    var fechaNacimiento = fechaNacimiento
        private set

    var email = email
        private set

    var password = password
        private set

    var sexo = sexo
        private set

    val edad: Int
        get() = calcularEdad()

    private fun calcularEdad(): Int {
        return Period.between(
            fechaNacimiento,
            LocalDate.now()
        ).years
    }
}