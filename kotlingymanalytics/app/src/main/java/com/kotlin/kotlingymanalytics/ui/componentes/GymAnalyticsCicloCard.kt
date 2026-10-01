package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.room.relations.CicloCompleto
import com.kotlin.kotlingymanalytics.ui.theme.AzulClaro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@Composable
fun GymAnalyticsCicloCard(
    ciclo: CicloCompleto
) {
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
                titulo = "Ciclo: ${ciclo.ciclo.nombre}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Inicio: ${ciclo.ciclo.fechaInicio}",
                color = BlancoCrema
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Duración: ${ciclo.ciclo.cantidadSemanas} semanas",
                color = BlancoCrema
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Estado: ${if (ciclo.ciclo.activo) "ACTIVO" else "INACTIVO"}",
                color = if (ciclo.ciclo.activo) {
                    RojoOscuro
                } else {
                    BlancoCrema
                },
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            HorizontalDivider(
                color = BlancoCrema.copy(alpha = 0.15f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Semanas",
                color = BlancoCrema,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            ciclo.semanas
                .sortedBy { it.semana.numeroSemana }
                .forEach { semana ->

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {

                        Text(
                            text = "Semana ${semana.semana.numeroSemana + 1}",
                            color = BlancoCrema
                        )

                        Text(
                            text = semana.rutina.rutina.nombre,
                            color = AzulClaro,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
        }
    }
}