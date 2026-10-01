package com.kotlin.kotlingymanalytics.ui.componentes


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.core.utils.nombreDia
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.room.relations.EjercicioAsignadoConDetalle
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@Composable
fun GymAnalyticsDiaRutina(
    dia: DiaSemana,
    ejercicios: List<EjercicioAsignadoConDetalle>
) {
    var expandido by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    expandido = !expandido
                }
                .padding(vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = if (expandido) "▼" else "▶",
                color = if (expandido) RojoOscuro else AzulClaro,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = nombreDia(dia),
                color = BlancoCrema,
                fontWeight = FontWeight.Bold
            )
        }

        if (expandido) {

            ejercicios.forEach { ejercicio ->

                GymAnalyticsEjercicioRutina(
                    ejercicio = ejercicio
                )
            }
        }
    }
}