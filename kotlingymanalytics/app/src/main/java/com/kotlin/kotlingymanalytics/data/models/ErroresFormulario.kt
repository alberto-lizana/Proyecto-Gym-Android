package com.kotlin.kotlingymanalytics.data.models

data class ErroresFormulario(

    val nombre: MutableMap<String, String> = mutableMapOf(),
    val appat: MutableMap<String, String> = mutableMapOf(),
    val apmat: MutableMap<String, String> = mutableMapOf(),
    val fechaNacimiento: MutableMap<String, String> = mutableMapOf(),
    val email: MutableMap<String, String> = mutableMapOf(),
    val password: MutableMap<String, String> = mutableMapOf()

) {
    val esValido: Boolean
        get() = nombre.isEmpty() &&
                appat.isEmpty() &&
                apmat.isEmpty() &&
                fechaNacimiento.isEmpty() &&
                email.isEmpty() &&
                password.isEmpty()

}
