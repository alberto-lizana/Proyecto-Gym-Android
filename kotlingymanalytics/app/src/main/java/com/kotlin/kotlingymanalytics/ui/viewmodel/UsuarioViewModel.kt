package com.kotlin.kotlingymanalytics.ui.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.kotlingymanalytics.core.utils.crearUsuariosBase
import com.kotlin.kotlingymanalytics.core.utils.validarFormulario
import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import com.kotlin.kotlingymanalytics.data.models.AuthResponse
import com.kotlin.kotlingymanalytics.data.models.CodigoRecuperarResponse
import com.kotlin.kotlingymanalytics.data.models.ErroresFormulario
import com.kotlin.kotlingymanalytics.data.models.LoginRequest
import com.kotlin.kotlingymanalytics.data.models.RecuperarPasswordRequest
import com.kotlin.kotlingymanalytics.data.models.RestablecerPasswordRequest
import com.kotlin.kotlingymanalytics.data.models.Usuario
import com.kotlin.kotlingymanalytics.data.models.UsuarioRequest
import com.kotlin.kotlingymanalytics.data.models.UsuarioResponse
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.remote.RetrofitInstance
import kotlinx.coroutines.launch
import java.time.LocalDate


class UsuarioViewModel : ViewModel() {

    val usuarios = mutableStateListOf<Usuario>()
    private val usuarioApi = RetrofitInstance.api

    init {
        crearUsuarioPredeterminado(crearUsuariosBase.toList())
    }

    fun crearUsuario(
        nombre: String,
        appat: String,
        apmat: String,
        fechaNacimiento: LocalDate?,
        email: String,
        password: String,
        sexo: SexoTipo,
        onSuccess: (UsuarioResponse) -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {

                val request = UsuarioRequest(
                    nombre = nombre.trim().lowercase(),
                    appat = appat.trim().lowercase(),
                    apmat = apmat.trim().lowercase(),
                    fechaNacimiento = fechaNacimiento!!.toString(),
                    email = email.trim().lowercase(),
                    password = password,
                    sexo = sexo
                )

                val response = usuarioApi.registrarUsuario(request)

                onSuccess(response)

            } catch (e: Exception) {

                e.printStackTrace()

                onError()
            }
        }
    }


    fun crearUsuarioPredeterminado(listaDeUsuarios: List<Usuario>) {
        for (u in listaDeUsuarios) {
            usuarios.add(u)
        }
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

    fun login(
        email: String,
        password: String,
        onSuccess: (AuthResponse) -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {

            try {

                val response = usuarioApi.iniciarSesion(
                    LoginRequest(
                        email = email.trim().lowercase(),
                        password = password
                    )
                )

                onSuccess(response)

            } catch (e: Exception) {

                onError()
            }
        }
    }

    fun obtenerUsuario(
        email: String,
        token: String,
        onSuccess: (UsuarioResponse) -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {
            try {

                val response = usuarioApi.obtenerUsuario(
                    token = "Bearer $token"
                )

                onSuccess(response)

            } catch (e: Exception) {

                e.printStackTrace()

                onError()
            }
        }
    }

    fun gestionSessionManager(
        usuarioResponse: UsuarioResponse,
        token: String
    ) {
        val usuario = Usuario(
            nombre = usuarioResponse.nombre,
            appat = usuarioResponse.appat,
            apmat = usuarioResponse.apmat,
            fechaNacimiento = LocalDate.parse(
                usuarioResponse.fechaNacimiento
            ),
            email = usuarioResponse.email,
            password = "",
            sexo = SexoTipo.valueOf(usuarioResponse.sexo)
        )

        SessionManager.iniciarSesion(
            usuario = usuario,
            token = token
        )
    }

    fun devolverUsuario(
        email: String
    ): Usuario {
        val usuario: Usuario = usuarios.find { u ->
            u.email == email.trim().lowercase()
        }!!
        return usuario
    }

    fun recuperarPassword(
        email: String,
        onSuccess: (CodigoRecuperarResponse) -> Unit,
        onError: () -> Unit
    ) {
        viewModelScope.launch {

            try {

                val response: CodigoRecuperarResponse = usuarioApi.recuperarPassword(
                    RecuperarPasswordRequest(
                        email = email.trim().lowercase()
                    )
                )

                onSuccess(response)

            } catch (e: Exception) {

                onError()
            }
        }
    }

    fun restablecerPassword(
        email: String,
        codigo: String,
        nuevaPassword: String,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ){
        viewModelScope.launch {

            try {

                usuarioApi.restablecerPassword(
                    RestablecerPasswordRequest(
                        email = email.trim().lowercase(),
                        codigo = codigo,
                        nuevaPassword = nuevaPassword
                    )
                )

                onSuccess("Contraseña restablecida con éxito")

            } catch (e: Exception) {

                onError()
            }
        }
    }
}