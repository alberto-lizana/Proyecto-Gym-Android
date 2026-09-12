package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.data.enums.EnumLogoType
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.componentes.EmailIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsLabel
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTextInput
import com.kotlin.kotlingymanalytics.ui.componentes.LockIcon
import com.kotlin.kotlingymanalytics.ui.componentes.Logo

import androidx.compose.ui.platform.LocalContext
import com.kotlin.kotlingymanalytics.core.utils.SoundManager
import com.kotlin.kotlingymanalytics.core.utils.vibrarError
import com.kotlin.kotlingymanalytics.data.enums.AlertTipo
import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import com.kotlin.kotlingymanalytics.data.models.Usuario
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsAlert
import com.kotlin.kotlingymanalytics.ui.viewmodel.usuarioViewModel
import java.time.LocalDate


@Composable
fun GymAnalyticsLogin(
    onLogin: () -> Unit,
    onCreate: () -> Unit,
    toRecuperar: () -> Unit,
    viewModel: usuarioViewModel
) {

    val usuario: Usuario = Usuario(
            nombre = "alberto",
            appat = "lizana",
            apmat = "rojas",
            fechaNacimiento = LocalDate.of(1994, 9, 15),
            email = "alberto@gmail.com",
            password = "123456789",
            sexo = SexoTipo.MASCULINO
        )

    var mostrarAlert by remember { mutableStateOf(false) }
    var tipoAlert by remember { mutableStateOf(AlertTipo.ERROR) }
    var mensajeAlert by remember { mutableStateOf("") }

    val context = LocalContext.current

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var error by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.Transparent
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.linearGradient(
                        colors = listOf(
                            FondoOscuro,
                            AzulOscuro
                        )
                    )
                )
        )
        {
            Card(
                elevation = CardDefaults.cardElevation(1.dp),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center),

                colors = CardDefaults.cardColors(
                    containerColor = FondoOscuro.copy(alpha = 0.55f)
                )
            ) {

                Column(
                    modifier = Modifier.padding(25.dp)
                ) {
                    Logo(
                        type = EnumLogoType.PRINCIPAL,
                        modifier = Modifier
                            .size(200.dp)
                            .align(Alignment.CenterHorizontally),
                        contentDescription = "Logo GymAnalytics"
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    GymAnalyticsLabel(
                        text = "Email",
                        modifier = Modifier.padding(bottom = 2.dp)
                    )

                    GymAnalyticsTextInput(
                        value = email,
                        onValueChange = {
                            email = it
                            error = ""
                        },
                        placeholder = "Ingresa tu email",
                        keyboardType = KeyboardType.Email,
                        leadingIcon = {
                            EmailIcon(
                                color = BlancoCrema,
                                modifier = Modifier
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    )  {
                        GymAnalyticsLabel(
                            text = "Contraseña",
                            modifier = Modifier.padding(bottom = 2.dp)
                        )

                        Text (
                            text = "¿Olvidaste tu contraseña?",
                            color = AzulClaro,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .clickable {
                                    toRecuperar()
                                }
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    GymAnalyticsTextInput(
                        value = password,
                        onValueChange = {
                            password = it
                            error = ""
                        },
                        placeholder = "Ingresa tu contraseña",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            LockIcon(
                                color = BlancoCrema,
                                modifier = Modifier
                            )
                        }
                    )

                    if (error.isNotEmpty()) {
                        Text(
                            text = error,
                            color = Color.Red,
                            fontSize = 13.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    GymAnalyticsButton(
                        text = "INICIAR SESION",
                        containerColor = AzulOscuro,
                        onClick = {

                            viewModel.crearUsuarioPredeterminado(usuario)

                            val credencialesCorrectas = viewModel.validarLogin(
                                email = email,
                                password = password
                            )


                            if (credencialesCorrectas) {
                                val usuario = viewModel.devolverUsuario(email)

                                SessionManager.iniciarSesion(usuario)

                                SoundManager.reproducirExito()

                                tipoAlert = AlertTipo.EXITO
                                mensajeAlert = "Credenciales correctas"
                                mostrarAlert = true

                            } else {
                                SoundManager.reproducirError()
                                context.vibrarError()

                                tipoAlert = AlertTipo.ERROR
                                mensajeAlert = "Credenciales incorrectas"
                                mostrarAlert = true
                            }
                        }
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    GymAnalyticsButton(
                        text = "CREAR CUENTA",
                        containerColor = Color.Transparent,
                        onClick = {
                            onCreate()
                        }
                    )
                }
            }

            if (mostrarAlert) {
                GymAnalyticsAlert(
                    tipo = tipoAlert,
                    mensaje = mensajeAlert,
                    onDismiss = {
                        mostrarAlert = false
                        if (tipoAlert == AlertTipo.EXITO) {
                            onLogin()
                        }
                        if (tipoAlert == AlertTipo.ERROR) {
                            error = "Email o contraseña incorrectos"
                        }
                    }
                )
            }

        }
    }
}

