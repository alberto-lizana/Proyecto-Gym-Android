package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsDato
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@Composable
fun GymAnalyticsPerfil(
    onLogout: () -> Unit
) {
    val usuario by SessionManager.usuarioActual.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        GymAnalyticsTitulo(
            titulo = "Perfil"
        )

        Spacer(modifier = Modifier.height(8.dp))

        usuario?.let {
            GymAnalyticsCard(
                titulo = "Información Personal"
            ) {
                GymAnalyticsDato(
                    nombre = "Nombre",
                    valor = it.nombre
                )

                GymAnalyticsDato(
                    nombre = "Apellidos",
                    valor = "${it.appat} ${it.apmat}"
                )

                GymAnalyticsDato(
                    nombre = "Edad",
                    valor = "${it.edad} años"
                )

                GymAnalyticsDato(
                    nombre = "Sexo",
                    valor = it.sexo.name.lowercase()
                )
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )

        GymAnalyticsButton(
            text = "CERRAR SESION",
            containerColor = RojoOscuro,
            onClick = onLogout,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        )
    }
}