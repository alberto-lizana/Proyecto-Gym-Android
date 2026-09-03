package com.kotlin.kotlingymanalytics.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.kotlin.kotlingymanalytics.core.utils.validarFormulario
import com.kotlin.kotlingymanalytics.data.models.ErroresFormulario
import com.kotlin.kotlingymanalytics.data.models.Usuario
import java.time.LocalDate


class usuarioViewModel : ViewModel() {

    val usuarios = mutableStateListOf<Usuario>()

    fun crearUsuario(
        nombre: String,
        appat: String,
        apmat: String,
        fechaNacimiento: LocalDate?,
        email: String,
        password: String,
    ) {
        val usuario = Usuario(
            nombre = nombre.trim().lowercase(),
            appat = appat.trim().lowercase(),
            apmat = apmat.trim().lowercase(),
            fechaNacimiento = fechaNacimiento!!,
            email = email.trim().lowercase(),
            password = password
        )

        usuarios.add(usuario)
    }

    fun validarDatosFormulario(
        nombre: String,
        appat: String,
        apmat: String,
        fechaNacimiento: LocalDate?,
        email: String,
        password: String,
        repeatPassword: String
    ): ErroresFormulario {

        val nombreNormalizado = nombre.trim().lowercase()
        val appatNormalizado = appat.trim().lowercase()
        val apmatNormalizado = apmat.trim().lowercase()
        val emailNormalizado = email.trim().lowercase()

        return validarFormulario(
            nombreNormalizado,
            appatNormalizado,
            apmatNormalizado,
            fechaNacimiento,
            emailNormalizado,
            password,
            repeatPassword,
            usuarios
        )
    }

    fun validarLogin(
        email: String,
        password: String
    ): Boolean {

        return usuarios.any { usuario ->
            usuario.email == email.trim().lowercase() &&
                    usuario.password == password
        }
    }

}