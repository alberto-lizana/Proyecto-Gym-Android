package com.kotlin.kotlingymanalytics.core.utils

import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import com.kotlin.kotlingymanalytics.data.models.ErroresFormulario
import com.kotlin.kotlingymanalytics.data.models.Usuario
import java.time.LocalDate

fun validarFormulario(
    nombre: String,
    appat: String,
    apmat: String,
    fechaNacimiento: LocalDate?,
    email: String,
    password: String,
    repeatPassword: String,
    usuarios: List<Usuario>
): ErroresFormulario {
    val errores = ErroresFormulario()
    validarNombre(nombre, errores.nombre)
    validarAppat(appat, errores.appat)
    validarApmat(apmat, errores.apmat)
    validarFechaNacimiento(fechaNacimiento, errores.fechaNacimiento)
    validarEmail(email, errores.email, usuarios)
    validarPassword(password, errores.password)
    validarPasswordMatcher(password, repeatPassword, errores.password)
    return errores
}

fun validarNombre(
    nombre: String,
    errores: MutableMap<String, String>
){
    val nombreCampo = "Nombre"

    if (nombre.isBlank()) {
        errores["vacio"] = campoVacio(nombreCampo)
    }

    val regex = Regex(
        "^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:\\s+[A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$"
    )

    if (!regex.matches(nombre)) {
        errores["caracteres invalidos"] = "El nombre solo puede contener letras"
    }

    val soloLetras = nombre.replace("\\s".toRegex(), "")

    if (soloLetras.length < 3) {
        errores["corto"] = campoLongitud(nombreCampo, 3, 50)
    }

    if (soloLetras.length > 50) {
        errores["largo"] = campoLongitud(nombreCampo, 3, 50)
    }
}

fun validarAppat(
    appat: String,
    errores: MutableMap<String, String>
) {

    val nombreCampo = "Apellido Paterno"

    if (appat.isBlank()) {
        errores["vacio"] = campoVacio(nombreCampo)
    }

    val regex = Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[-'\\s][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")

    if (!regex.matches(appat)) {
        errores["caracteres_invalidos"] = "El apellido solo puede contener letras"
    }

    val soloLetras = appat.replace("[-'\\s]".toRegex(), "")

    if (soloLetras.length < 3) {
        errores["corto"] = campoLongitud(nombreCampo, 3, 50)
    }

    if (soloLetras.length > 50) {
        errores["largo"] = campoLongitud(nombreCampo, 0, 50)
    }
}

// Al ser opcional si viene vacío no importa pero si viene lo validamos.
fun validarApmat(
    apmat: String,
    errores: MutableMap<String, String>
) {
    val nombreCampo = "Apellido Materno"

    if (apmat.isBlank()) {
        return
    } else {
        val regex = Regex("^[A-Za-zÁÉÍÓÚáéíóúÑñ]+(?:[-'\\s][A-Za-zÁÉÍÓÚáéíóúÑñ]+)*$")

        if (!regex.matches(apmat)) {
            errores["caracteres_invalidos"] = "El apellido solo puede contener letras"
        }

        val soloLetras = apmat.replace("[-'\\s]".toRegex(), "")

        if (soloLetras.length > 50) {
            errores["largo"] = campoLongitud(nombreCampo, 0, 50)
        }
    }
}

fun validarFechaNacimiento(
    fechaNac: LocalDate?,
    errores: MutableMap<String, String>
) {
    if (fechaNac == null) {
        errores["vacio"] = "Debe ingresar una fecha de nacimiento"
        return
    }

    val hoy = LocalDate.now()

    val fechaMinima = hoy.minusYears(100)
    val fechaMaxima = hoy.minusYears(5)

    if (fechaNac.isBefore(fechaMinima)) {
        errores["muy_antigua"] =
            "La fecha de nacimiento no puede ser de hace más de 100 años"
    }

    if (fechaNac.isAfter(fechaMaxima)) {
        errores["muy_reciente"] = "La persona debe tener al menos 5 años"
    }
}

fun validarEmail(
    email: String,
    errores: MutableMap<String, String>,
    usuarios: List<Usuario>
) {
    val nombreCampo = "Email"

    if(emailYaExiste(email, usuarios)){
        errores["email_existe"] = "El email ingresado ya existe"
    }

    if (email.isBlank()) {
        errores["vacio"] = campoVacio(nombreCampo)
    }

    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")

    if (!regex.matches(email)) {
        errores["formato_invalido"] = "El email no tiene un formato válido"
    }
}

fun validarPassword(
    password: String,
    errores: MutableMap<String, String>
) {
    val nombreCampo = "Contraseña"

    if (password.isBlank()) {
        errores["vacio"] = campoVacio(nombreCampo)
    }

    if (password.length !in 10..50) {
        errores["longitud"] = campoLongitud(nombreCampo, 10, 50)
    }

    val regex = Regex("^(?!\\s)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?<!\\s).*$")

    if (!regex.matches(password)) {
        errores["formato_invalido"] =
            "La contraseña debe contener al menos una mayúscula, " +
            "una minúscula y un carácter especial, " +
            "sin espacios al inicio o final"
    }
}

fun validarPasswordMatcher(
    password: String,
    repeatPassword: String,
    errores: MutableMap<String, String>
){
    if (password != repeatPassword) {
        errores["no_coincide"] = "Las contraseñas no coinciden"
    }
}

fun campoVacio(campo: String): String {
    return "El campo $campo no puede estar vacío"
}

fun campoLongitud(campo: String, minLongitud: Int, maxLongitud: Int): String {
    return "El campo $campo requiere una longitud mínima de $minLongitud y la maxima $maxLongitud"
}

fun emailYaExiste(
    email: String,
    usuarios: List<Usuario>
): Boolean {

    return usuarios.any { usuario ->
        usuario.getEmail() == email.trim().lowercase()
    }
}


val crearUsuariosBase: Array<Usuario> = arrayOf(

    Usuario(
        nombre = "alberto",
        appat = "lizana",
        apmat = "rojas",
        fechaNacimiento = LocalDate.of(1994, 9, 15),
        email = "alberto@gmail.com",
        password = "123456789",
        sexo = SexoTipo.MASCULINO
    ),

    Usuario(
        nombre = "camila",
        appat = "gonzalez",
        apmat = "perez",
        fechaNacimiento = LocalDate.of(1997, 3, 22),
        email = "camila@gmail.com",
        password = "987654321",
        sexo = SexoTipo.FEMENINO
    ),

    Usuario(
        nombre = "diego",
        appat = "muñoz",
        apmat = "soto",
        fechaNacimiento = LocalDate.of(1992, 7, 10),
        email = "diego@gmail.com",
        password = "123123123",
        sexo = SexoTipo.MASCULINO
    ),

    Usuario(
        nombre = "valentina",
        appat = "rojas",
        apmat = "castillo",
        fechaNacimiento = LocalDate.of(2000, 11, 5),
        email = "valentina@gmail.com",
        password = "456456456",
        sexo = SexoTipo.FEMENINO
    ),

    Usuario(
        nombre = "sebastian",
        appat = "torres",
        apmat = "contreras",
        fechaNacimiento = LocalDate.of(1995, 1, 28),
        email = "sebastian@gmail.com",
        password = "789789789",
        sexo = SexoTipo.MASCULINO
    )
)
