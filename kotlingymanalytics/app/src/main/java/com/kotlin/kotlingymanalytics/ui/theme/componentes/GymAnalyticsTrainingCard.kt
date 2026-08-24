package com.kotlin.kotlingymanalytics.ui.theme.componentes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro

@Composable
fun GymAnalyticsTrainingCard(
    entrenamiento: String,
    ejercicios: Int,
    seriesTotales: Int,
    duracionEstimada: Int,
    onStart: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = entrenamiento,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Ejercicios")
                Text("$ejercicios")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Series totales")
                Text("$seriesTotales")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Duración estimada")
                Text("$duracionEstimada min")
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            GymAnalyticsButton(
                text = "Comenzar Entrenamiento",
                containerColor = AzulOscuro,
                onClick = onStart,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}