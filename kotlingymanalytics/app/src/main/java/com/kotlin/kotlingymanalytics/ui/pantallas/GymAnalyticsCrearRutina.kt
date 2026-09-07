package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsDiaSelector
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsOutlinedInput
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro
import com.kotlin.kotlingymanalytics.ui.viewmodel.rutinaViewModel

@Composable
fun GymAnalyticsCrearRutina(
    onContinuar: () -> Unit,
    viewModel: rutinaViewModel
) {
    var nombreRutina by remember { mutableStateOf("") }

    var diasSeleccionados by remember { mutableStateOf(setOf<DiaSemana>()) }

    val dias = listOf(
        DiaSemana.LUNES to "L",
        DiaSemana.MARTES to "M",
        DiaSemana.MIERCOLES to "X",
        DiaSemana.JUEVES to "J",
        DiaSemana.VIERNES to "V",
        DiaSemana.SABADO to "S",
        DiaSemana.DOMINGO to "D"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(FondoOscuro, AzulOscuro)
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {

            GymAnalyticsTitulo(
                titulo = "Crear rutina",
            )

            Spacer(modifier = Modifier.height(8.dp))

            GymAnalyticsOutlinedInput(
                value = nombreRutina,
                onValueChange = { nombreRutina = it },
                label = "Nombre de rutina",
                keyboardType = KeyboardType.Text
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Días de entrenamiento",
                color = BlancoCrema,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                dias.forEach { (dia, etiqueta) ->

                    GymAnalyticsDiaSelector(
                        nombre = etiqueta,
                        seleccionado = dia in diasSeleccionados,
                        onClick = {
                            diasSeleccionados =
                                if (dia in diasSeleccionados) {
                                    diasSeleccionados - dia
                                } else {
                                    diasSeleccionados + dia
                                }
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            GymAnalyticsButton(
                text = "CONTINUAR",
                containerColor = RojoOscuro,
                onClick = {
                    viewModel.configurarRutina(nombreRutina, diasSeleccionados)
                    onContinuar()
                },
                enabled = nombreRutina.isNotBlank()
                        && diasSeleccionados.isNotEmpty(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}