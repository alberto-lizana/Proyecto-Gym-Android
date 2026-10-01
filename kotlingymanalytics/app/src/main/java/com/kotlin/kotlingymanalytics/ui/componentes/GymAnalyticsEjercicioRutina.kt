package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.core.utils.formatoRepeticiones
import com.kotlin.kotlingymanalytics.room.relations.EjercicioAsignadoConDetalle
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema

@Composable
fun GymAnalyticsEjercicioRutina(
    ejercicio: EjercicioAsignadoConDetalle
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 12.dp,
                vertical = 8.dp
            )
    ) {

        Text(
            text = ejercicio.ejercicio.nombre,
            color = BlancoCrema,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${ejercicio.esquemaSeries.numeroSeries} × ${
                formatoRepeticiones(ejercicio.esquemaReps)
            }",
            color = AzulClaro,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = buildString {
                append(
                    "Peso: ${
                        ejercicio.asignado.peso?.let { "$it kg" } ?: "—"
                    }"
                )
                append(" · RIR: ${ejercicio.asignado.rir ?: "—"}")
                append(" · RPE: ${ejercicio.asignado.rpe ?: "—"}")
            },
            color = BlancoCrema
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "Descanso: ${ejercicio.asignado.descansoSegundos} s",
            color = BlancoCrema
        )
    }
}