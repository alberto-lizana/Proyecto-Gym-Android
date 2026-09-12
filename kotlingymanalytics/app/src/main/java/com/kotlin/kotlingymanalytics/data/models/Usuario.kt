package com.kotlin.kotlingymanalytics.data.models
import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import java.time.LocalDate
import java.time.Period

data class Usuario (

    // val id: Long,
    private var nombre: String,
    private var appat: String,
    private var apmat: String?,
    private var fechaNacimiento: LocalDate,
    private var email: String,
    private var password: String,
    private var sexo: SexoTipo

) {
    val edad: Int
        get() = calcularEdad()

    private fun calcularEdad(): Int {
        return Period.between(
            fechaNacimiento,
            LocalDate.now()
        ).years
    }

    // GETTERS / SETTERS

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getAppat(): String {
        return appat
    }

    fun setAppat(appat: String) {
        this.appat = appat
    }

    fun getApmat(): String? {
        return apmat
    }

    fun setApmat(apmat: String?) {
        this.apmat = apmat
    }

    fun getFechaNacimiento(): LocalDate {
        return fechaNacimiento
    }

    fun setFechaNacimiento(fechaNacimiento: LocalDate) {
        this.fechaNacimiento = fechaNacimiento
    }

    fun getEmail(): String {
        return email
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getPassword(): String {
        return password
    }

    fun setPassword(password: String) {
        this.password = password
    }

    fun getSexo(): SexoTipo {
        return sexo
    }

    fun setSexo(sexo: SexoTipo) {
        this.sexo = sexo
    }
}