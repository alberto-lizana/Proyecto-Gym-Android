package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.data.enums.EnumLogoType
import com.kotlin.kotlingymanalytics.data.models.CodigoRecuperarResponse
import com.kotlin.kotlingymanalytics.ui.componentes.EmailIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsLabel
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTextInput
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.componentes.Logo
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@Composable
fun GymAnalyticsRecoverPassword(
    onVolverLogin: () -> Unit,

    onRecuperar: (
        String,
        (CodigoRecuperarResponse) -> Unit,
        () -> Unit
    ) -> Unit,

    onRestablecer: (
        String,
        String,
        String,
        (String) -> Unit,
        () -> Unit
    ) -> Unit
) {

    var email by remember { mutableStateOf("") }
    var codigo by remember { mutableStateOf("") }
    var nuevaPassword by remember { mutableStateOf("") }

    var codigoGenerado by remember { mutableStateOf<String?>(null) }
    var resultado by remember { mutableStateOf<String?>(null) }

    var cargando by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        FondoOscuro,
                        AzulOscuro
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {

        Card(
            elevation = CardDefaults.cardElevation(1.dp),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
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

                Logo(
                    type = EnumLogoType.PRINCIPAL,
                    modifier = Modifier.size(140.dp),
                    contentDescription = "Logo GymAnalytics"
                )

                Spacer(modifier = Modifier.height(6.dp))

                GymAnalyticsTitulo(
                    titulo = "Recuperar contraseña"
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = if (codigoGenerado == null) {
                        "Ingresa tu correo para generar un código de recuperación."
                    } else {
                        "Ingresa el código y establece una nueva contraseña."
                    },
                    color = BlancoCrema,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // EMAIL

                GymAnalyticsLabel(
                    text = "Email",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                )

                GymAnalyticsTextInput(
                    value = email,
                    onValueChange = {
                        email = it
                        resultado = null
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

                Spacer(modifier = Modifier.height(12.dp))

                // PRIMERA ETAPA

                if (codigoGenerado == null) {

                    GymAnalyticsButton(
                        text = if (cargando) {
                            "PROCESANDO..."
                        } else {
                            "RECUPERAR CONTRASEÑA"
                        },
                        containerColor = RojoOscuro,
                        onClick = {

                            if (email.isBlank()) {
                                resultado = "Debes ingresar un email."
                                return@GymAnalyticsButton
                            }

                            cargando = true
                            resultado = null

                            onRecuperar(
                                email,
                                { response ->

                                    cargando = false

                                    codigoGenerado =
                                        response.codigoString
                                },
                                {
                                    cargando = false

                                    resultado =
                                        "No se pudo encontrar el usuario."
                                }
                            )
                        }
                    )
                }

                // SEGUNDA ETAPA

                if (codigoGenerado != null) {

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Código generado: ${codigoGenerado}",
                        color = AzulClaro,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    GymAnalyticsLabel(
                        text = "Código de recuperación",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 2.dp)
                    )

                    GymAnalyticsTextInput(
                        value = codigo,
                        onValueChange = {
                            codigo = it
                            resultado = null
                        },
                        placeholder = "Ingresa el código",
                        keyboardType = KeyboardType.Number
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    GymAnalyticsLabel(
                        text = "Nueva contraseña",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 2.dp)
                    )

                    GymAnalyticsTextInput(
                        value = nuevaPassword,
                        onValueChange = {
                            nuevaPassword = it
                            resultado = null
                        },
                        placeholder = "Ingresa tu nueva contraseña",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation()
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    GymAnalyticsButton(
                        text = if (cargando) {
                            "PROCESANDO..."
                        } else {
                            "RESTABLECER CONTRASEÑA"
                        },
                        containerColor = RojoOscuro,
                        onClick = {

                            if (codigo.isBlank()) {
                                resultado = "Debes ingresar el código."
                                return@GymAnalyticsButton
                            }

                            if (nuevaPassword.isBlank()) {
                                resultado =
                                    "Debes ingresar una nueva contraseña."
                                return@GymAnalyticsButton
                            }

                            cargando = true
                            resultado = null

                            onRestablecer(
                                email,
                                codigo,
                                nuevaPassword,

                                { mensaje ->

                                    cargando = false
                                    resultado = mensaje

                                },

                                {
                                    cargando = false
                                    resultado =
                                        "El código no es válido o ha expirado."
                                }
                            )
                        }
                    )
                }

                // RESULTADO

                resultado?.let { mensaje ->

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = mensaje,
                        color = BlancoCrema,
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(6.dp))

                TextButton(
                    onClick = onVolverLogin
                ) {
                    Text(
                        text = "Volver al inicio",
                        color = AzulClaro,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}