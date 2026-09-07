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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.core.utils.SoundManager
import com.kotlin.kotlingymanalytics.core.utils.vibrarError
import com.kotlin.kotlingymanalytics.data.enums.AlertTipo
import com.kotlin.kotlingymanalytics.data.enums.EnumLogoType
import com.kotlin.kotlingymanalytics.data.enums.SexoTipo
import com.kotlin.kotlingymanalytics.ui.componentes.EmailIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsAlert
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsDatePicker
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsLabel
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsOutlinedInput
import com.kotlin.kotlingymanalytics.ui.componentes.LockIcon
import com.kotlin.kotlingymanalytics.ui.componentes.Logo
import com.kotlin.kotlingymanalytics.ui.componentes.PersonIcon
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.viewmodel.usuarioViewModel
import java.time.LocalDate


@Composable
fun GymAnalyticsRegister(
    toLogin: () -> Unit,
    viewModel: usuarioViewModel
) {
    val context = LocalContext.current

    var mostrarAlert by remember { mutableStateOf(false) }
    var tipoAlert by remember { mutableStateOf(AlertTipo.ERROR) }
    var mensajeAlert by remember { mutableStateOf("") }

    var nombre by remember { mutableStateOf("") }
    var appat by remember { mutableStateOf("") }
    var apmat by remember { mutableStateOf("") }
    var fechaNacimiento: LocalDate? by remember { mutableStateOf(null) }
    var email by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf<SexoTipo>(SexoTipo.MASCULINO) }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }

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
        ) {

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
                    modifier = Modifier
                        .padding(25.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    // LOGO
                    Logo(
                        type = EnumLogoType.RESUMIDO,
                        modifier = Modifier.size(120.dp),
                        contentDescription = "Logo GymAnalytics"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // TITULO
                    Text(
                        text = "Crear cuenta",
                        color = BlancoCrema,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "Registra tus datos para comenzar",
                        color = BlancoCrema.copy(alpha = 0.65f),
                        fontSize = 13.sp
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )


                    // NOMBRE
                    GymAnalyticsOutlinedInput(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = "Nombre",
                        keyboardType = KeyboardType.Text,
                        leadingIcon = {
                            PersonIcon(
                                size = 24.dp
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // APELLIDO PATERNO
                    GymAnalyticsOutlinedInput(
                        value = appat,
                        onValueChange = { appat = it },
                        label = "Apellido paterno",
                        keyboardType = KeyboardType.Text,
                        leadingIcon = {
                            PersonIcon(
                                size = 24.dp
                            )
                        }
                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )


                    // APELLIDO MATERNO
                    GymAnalyticsOutlinedInput(
                        value = apmat,
                        onValueChange = { apmat = it },
                        label = "Apellido materno",
                        keyboardType = KeyboardType.Text,
                        leadingIcon = {
                            PersonIcon(
                                size = 24.dp
                            )
                        }
                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // SEXO
                    GymAnalyticsLabel(
                        text = "Sexo",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                sexo = SexoTipo.MASCULINO
                            }
                        ) {
                            RadioButton(
                                selected = sexo == SexoTipo.MASCULINO,
                                onClick = {
                                    sexo = SexoTipo.MASCULINO
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = AzulOscuro,
                                    unselectedColor = BlancoCrema
                                )
                            )

                            Text(
                                text = "Masculino",
                                color = BlancoCrema
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable {
                                sexo = SexoTipo.FEMENINO
                            }
                        ) {
                            RadioButton(
                                selected = sexo == SexoTipo.FEMENINO,
                                onClick = {
                                    sexo = SexoTipo.FEMENINO
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = AzulOscuro,
                                    unselectedColor = BlancoCrema
                                )
                            )

                            Text(
                                text = "Femenino",
                                color = BlancoCrema
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // FECHA NACIMIENTO
                    GymAnalyticsDatePicker(
                        value = fechaNacimiento,
                        onDateSelected = {
                            fechaNacimiento = it
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // EMAIL
                    GymAnalyticsOutlinedInput(
                        value = email,
                        onValueChange = { email = it },
                        label = "Email",
                        keyboardType = KeyboardType.Email,
                        leadingIcon = {
                            EmailIcon (
                                color = BlancoCrema,
                                modifier = Modifier,
                                size = 24.dp
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    // CONTRASEÑA
                    GymAnalyticsOutlinedInput(
                        value = password,
                        onValueChange = { password = it },
                        label = "Contraseña",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            LockIcon(
                                color = BlancoCrema,
                                modifier = Modifier,
                                size = 24.dp
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )


                    // REPETIR CONTRASEÑA
                    GymAnalyticsOutlinedInput(
                        value = repeatPassword,
                        onValueChange = { repeatPassword = it },
                        label = "Repetir Contraseña",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = {
                            LockIcon(
                                color = BlancoCrema,
                                modifier = Modifier,
                                size = 24.dp
                            )
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )

                    // CREAR CUENTA
                    GymAnalyticsButton(
                        text = "CREAR CUENTA",
                        containerColor = AzulOscuro,
                        onClick = {

                            val errores = viewModel.validarDatosFormulario(
                                nombre,
                                appat,
                                apmat,
                                fechaNacimiento,
                                email,
                                password,
                                repeatPassword
                            )

                            if (errores.esValido) {
                                viewModel.crearUsuario(
                                    nombre,
                                    appat,
                                    apmat,
                                    fechaNacimiento,
                                    email,
                                    password,
                                    sexo
                                )

                                SoundManager.reproducirExito()

                                tipoAlert = AlertTipo.EXITO
                                mensajeAlert = "Usuario creado correctamente"
                                mostrarAlert = true

                            } else {
                                SoundManager.reproducirError()
                                context.vibrarError()

                                tipoAlert = AlertTipo.ERROR
                                mensajeAlert = "No fue posible crear el usuario"
                                mostrarAlert = true

                            }
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    // VOLVER
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Text(
                            text = "¿Ya tienes una cuenta? ",
                            color = BlancoCrema.copy(alpha = 0.65f),
                            fontSize = 13.sp
                        )

                        Text(
                            text = "Inicia sesión",
                            color = AzulClaro,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable {
                                toLogin()
                            }
                        )
                    }
                }
            }
            if (mostrarAlert) {
                GymAnalyticsAlert(
                    tipo = tipoAlert,
                    mensaje = mensajeAlert,
                    onDismiss = {
                        mostrarAlert = false
                        if (tipoAlert == AlertTipo.EXITO) {
                            toLogin()
                        }
                    }
                )
            }
        }
    }
}
