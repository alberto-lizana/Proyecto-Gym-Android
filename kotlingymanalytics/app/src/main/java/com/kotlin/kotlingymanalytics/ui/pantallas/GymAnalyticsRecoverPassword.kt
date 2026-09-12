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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.data.enums.EnumLogoType
import com.kotlin.kotlingymanalytics.ui.componentes.EmailIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
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
    onRecuperar: (String) -> String
) {

    var email by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }

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
                modifier = Modifier.padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Logo(
                    type = EnumLogoType.PRINCIPAL,
                    modifier = Modifier
                        .size(160.dp),
                    contentDescription = "Logo GymAnalytics"
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsTitulo(
                    titulo = "Recuperar contraseña"
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ingresa tu correo electrónico para recuperar el acceso a tu cuenta.",
                    color = BlancoCrema,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

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

                Spacer(modifier = Modifier.height(16.dp))

                GymAnalyticsButton(
                    text = "RECUPERAR CONTRASEÑA",
                    containerColor = RojoOscuro,
                    onClick = {
                        resultado = onRecuperar(email)
                    }
                )

                resultado?.let { mensaje ->

                    Spacer(modifier = Modifier.height(16.dp))

                    GymAnalyticsCard(
                        titulo = "Resultado"
                    ) {
                        Text(
                            text = mensaje,
                            color = BlancoCrema,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

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
