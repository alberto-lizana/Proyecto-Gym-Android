package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.componentes.AddIcon
import com.kotlin.kotlingymanalytics.ui.componentes.CalendarIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsAccionHome
import com.kotlin.kotlingymanalytics.ui.componentes.MancuernaIcon
import com.kotlin.kotlingymanalytics.ui.componentes.DownloadIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsDato
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro


@Composable
fun GymAnalyticsHome(
    toCrearRutina: () -> Unit,
    onStart: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        GymAnalyticsTitulo(
            titulo = "Inicio"
        )

        Spacer(modifier = Modifier.height(8.dp))


        GymAnalyticsTitulo(
            titulo = "Entrenamiento de hoy",
            style = MaterialTheme.typography.titleLarge,
        )

        Card(
            elevation = CardDefaults.cardElevation(1.dp),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = FondoOscuro.copy(alpha = 0.55f)
            )
        ) {
            Column(modifier = Modifier.padding(20.dp)) {

                Spacer(modifier = Modifier.height(12.dp))

                GymAnalyticsCard(
                    titulo = "Piernas"
                ) {
                    GymAnalyticsDato(
                        nombre = "Ejercicios",
                        valor = "7"
                    )

                    GymAnalyticsDato(
                        nombre = "Series totales",
                        valor = "18"
                    )

                    GymAnalyticsDato(
                        nombre = "Duración estimada",
                        valor = "65"
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    GymAnalyticsButton(
                        text = "COMENZAR ENTRENAMIENTO",
                        containerColor = RojoOscuro,
                        onClick = onStart,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        GymAnalyticsTitulo(
            titulo = "Acciones",
            style = MaterialTheme.typography.titleLarge
        )

        Card(
            elevation = CardDefaults.cardElevation(1.dp),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = FondoOscuro.copy(alpha = 0.55f)
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {

                GymAnalyticsAccionHome(
                    titulo = "Crear rutina",
                    subtitulo = "Crea una nueva rutina",
                    icono = {
                        AddIcon()
                    },
                    onClick = { toCrearRutina() }
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsAccionHome(
                    titulo = "Mis rutinas",
                    subtitulo = "Ver y gestionar tus rutinas",
                    icono = {
                        MancuernaIcon()
                    },
                    onClick = {
                        // GymAnalyticsMisRutinas
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsAccionHome(
                    titulo = "Calendario",
                    subtitulo = "Consulta tus entrenamientos programados",
                    icono = {
                        CalendarIcon()
                    },
                    onClick = {
                        // GymAnalyticsCalendario
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsAccionHome(
                    titulo = "Exportar datos",
                    subtitulo = "Descarga tus rutinas y progreso",
                    icono = {
                        DownloadIcon()
                    },
                    onClick = {
                        // GymAnalyticsExportar
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
