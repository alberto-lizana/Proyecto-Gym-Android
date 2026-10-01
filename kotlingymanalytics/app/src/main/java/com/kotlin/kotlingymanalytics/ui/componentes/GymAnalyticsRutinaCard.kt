package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.room.relations.RutinaCompleta
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro

@Composable
fun GymAnalyticsRutinaCard(
    rutina: RutinaCompleta
) {
    val ejerciciosPorDia = rutina.ejerciciosPorDia

    val dias = ejerciciosPorDia.keys
        .sortedBy { it.ordinal }

    val cantidadEjercicios = rutina.ejercicios.size

    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = FondoOscuro.copy(alpha = 0.55f)
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {

            GymAnalyticsTitulo(
                titulo = rutina.rutina.nombre
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${dias.size} días · $cantidadEjercicios ejercicios",
                color = BlancoCrema,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                color = BlancoCrema.copy(alpha = 0.15f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            dias.forEach { dia ->

                GymAnalyticsDiaRutina(
                    dia = dia,
                    ejercicios = ejerciciosPorDia[dia].orEmpty()
                )
            }
        }
    }
}