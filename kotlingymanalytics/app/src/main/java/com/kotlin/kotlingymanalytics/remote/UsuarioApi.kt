package com.kotlin.kotlingymanalytics.remote

import com.kotlin.kotlingymanalytics.data.models.AuthResponse
import com.kotlin.kotlingymanalytics.data.models.CodigoRecuperarResponse
import com.kotlin.kotlingymanalytics.data.models.LoginRequest
import com.kotlin.kotlingymanalytics.data.models.RecuperarPasswordRequest
import com.kotlin.kotlingymanalytics.data.models.RestablecerPasswordRequest
import com.kotlin.kotlingymanalytics.data.models.UsuarioRequest
import com.kotlin.kotlingymanalytics.data.models.UsuarioResponse

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsuarioApi {

    @GET("api/auth/obtener")
    suspend fun obtenerUsuario(
        @Header("Authorization") token: String
    ): UsuarioResponse

    @POST("api/auth/registrar")
    suspend fun registrarUsuario(
        @Body usuario: UsuarioRequest
    ): UsuarioResponse

    @POST("api/auth/login")
    suspend fun iniciarSesion(
        @Body request: LoginRequest
    ): AuthResponse

    @POST("api/auth/recuperar")
    suspend fun recuperarPassword(
        @Body request: RecuperarPasswordRequest
    ): CodigoRecuperarResponse

    @POST("api/auth/restablecer")
    suspend fun restablecerPassword(
        @Body request: RestablecerPasswordRequest
    )
}