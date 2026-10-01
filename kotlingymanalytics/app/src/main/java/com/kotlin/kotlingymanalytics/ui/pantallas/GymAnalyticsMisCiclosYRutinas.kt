package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCicloCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsRutinaCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.viewmodel.CicloViewModel

@Composable
fun GymAnalyticsMisCiclosRutinas(
    cicloViewModel: CicloViewModel
) {
    val ciclos = cicloViewModel.ciclos.collectAsState().value
    val rutinas = cicloViewModel.rutinasCompletas.collectAsState().value

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
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    horizontal = 24.dp,
                    vertical = 20.dp
                )
        ) {

            GymAnalyticsTitulo(
                titulo = "Mis ciclos y rutinas"
            )

            Spacer(modifier = Modifier.height(24.dp))

            GymAnalyticsTitulo(
                titulo = "Ciclos"
            )

            Spacer(modifier = Modifier.height(12.dp))

            ciclos.forEach { ciclo ->
                GymAnalyticsCicloCard(ciclo)

                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            GymAnalyticsTitulo(
                titulo = "Rutinas"
            )

            Spacer(modifier = Modifier.height(12.dp))

            rutinas.forEach { rutina ->
                GymAnalyticsRutinaCard(rutina)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}