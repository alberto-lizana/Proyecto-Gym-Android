package com.kotlin.kotlingymanalytics.remote.api

import com.kotlin.kotlingymanalytics.remote.dto.AuthResponse
import com.kotlin.kotlingymanalytics.remote.dto.CodigoRecuperarResponse
import com.kotlin.kotlingymanalytics.remote.dto.LoginRequest
import com.kotlin.kotlingymanalytics.remote.dto.RecuperarPasswordRequest
import com.kotlin.kotlingymanalytics.remote.dto.RestablecerPasswordRequest
import com.kotlin.kotlingymanalytics.remote.dto.UsuarioRequest
import com.kotlin.kotlingymanalytics.remote.dto.UsuarioResponse

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