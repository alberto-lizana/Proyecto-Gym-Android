package com.kotlin.kotlingymanalytics.ui.theme.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Download
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.theme.componentes.GymAnalyticsTrainingCard
import com.kotlin.kotlingymanalytics.ui.theme.componentes.MancuernaIcon

@Composable
fun GymAnalyticsHome() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Inicio",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Entrenamiento de hoy",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        GymAnalyticsTrainingCard(
            entrenamiento = "Piernas",
            ejercicios = 7,
            seriesTotales = 18,
            duracionEstimada = 65,
            onStart = {
                // GymAnalyticsEntrenamiento
            }
        )


        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Text(
            text = "Acciones",
            style = MaterialTheme.typography.titleLarge
        )

        ListItem(
            headlineContent = {
                Text("Mis rutinas")
            },
            supportingContent = {
                Text("Ver y gestionar tus rutinas")
            },
            leadingContent = {
                MancuernaIcon(
                    contentDescription = null
                )
            },
            modifier = Modifier.clickable {
                // GymAnalyticsMisRutinas
            }
        )

        ListItem(
            headlineContent = {
                Text("Crear rutina")
            },
            supportingContent = {
                Text("Crea una nueva rutina")
            },
            leadingContent = {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                )
            },
            modifier = Modifier.clickable {
                // GymAnalyticsCrearRutina
            }
        )

        ListItem(
            headlineContent = {
                Text("Calendario")
            },
            supportingContent = {
                Text("Consulta tus entrenamientos programados")
            },
            leadingContent = {
                Icon(
                    Icons.Default.CalendarMonth,
                    contentDescription = null
                )
            },
            modifier = Modifier.clickable {
                // GymAnalyticsCalendario
            }
        )

        ListItem(
            headlineContent = {
                Text("Exportar datos")
            },
            supportingContent = {
                Text("Descarga tus rutinas y progreso")
            },
            leadingContent = {
                Icon(
                    Icons.Default.Download,
                    contentDescription = null
                )
            },
            modifier = Modifier.clickable {
                // GymAnalyticsExportar
            }
        )
    }
}