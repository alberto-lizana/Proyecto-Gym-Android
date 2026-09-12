package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.core.utils.ejerciciosBase
import com.kotlin.kotlingymanalytics.core.utils.crearEsquemaDeSeriesPredeterminados
import com.kotlin.kotlingymanalytics.core.utils.esquemaRepsBase
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.data.models.Ejercicio
import com.kotlin.kotlingymanalytics.data.models.EsquemaReps
import com.kotlin.kotlingymanalytics.data.models.EsquemaSeries
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsOutlinedInput
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.componentes.SelectorEsquemaReps
import com.kotlin.kotlingymanalytics.ui.componentes.SelectorEsquemaSeries
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro
import com.kotlin.kotlingymanalytics.ui.viewmodel.rutinaViewModel

@Composable
fun GymAnalyticsAsignarEjercicios(
    onAsignarEjercicios: () -> Unit,
    viewModel: rutinaViewModel
) {
    val nombreRutina: String = viewModel.nombreRutina
    val diasSeleccionados: Set<DiaSemana> = viewModel.diasSeleccionados
    val ejerciciosPorDia = viewModel.ejerciciosPorDia

    val esquemasSeries = remember { crearEsquemaDeSeriesPredeterminados() }
    val esquemasReps = esquemaRepsBase.toList()
    val listaEjercicios = ejerciciosBase.toList()

    // Estado local: solo importa mientras el diálogo está abierto
    var diaAgregandoEjercicio by remember { mutableStateOf<DiaSemana?>(null) }
    var ejercicioSeleccionado by remember { mutableStateOf<Ejercicio?>(null) }
    var esquemaSeriesSeleccionado by remember { mutableStateOf<EsquemaSeries?>(null) }
    var esquemaRepsSeleccionado by remember { mutableStateOf<EsquemaReps?>(null) }
    var peso by remember { mutableStateOf("") }
    var descanso by remember { mutableStateOf("") }

    fun cerrarDialogo() {
        diaAgregandoEjercicio = null
        ejercicioSeleccionado = null
        esquemaSeriesSeleccionado = null
        esquemaRepsSeleccionado = null
        peso = ""
        descanso = ""
    }

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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {

            GymAnalyticsTitulo(titulo = "Asignación de Ejercicios")

            Spacer(modifier = Modifier.height(8.dp))

            GymAnalyticsTitulo(
                titulo = nombreRutina,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            diasSeleccionados.forEach { dia ->

                GymAnalyticsCard(titulo = nombreDia(dia)) {

                    ejerciciosPorDia[dia]
                        .orEmpty()
                        .forEach { ejercicioAsignado ->
                            GymAnalyticsTitulo(
                                titulo = ejercicioAsignado.getEjercicio().getNombre(),
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium
                            )
                        }

                    TextButton(
                        onClick = { diaAgregandoEjercicio = dia },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        GymAnalyticsTitulo(
                            titulo = "+ AGREGAR EJERCICIO",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Medium,
                            color = RojoOscuro
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            GymAnalyticsButton(
                text = "CONTINUAR",
                containerColor = RojoOscuro,
                onClick = { onAsignarEjercicios() },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

    if (diaAgregandoEjercicio != null) {

        AlertDialog(
            onDismissRequest = { cerrarDialogo() },

            title = {
                Text(
                    text = if (ejercicioSeleccionado == null) "Agregar ejercicio" else "Configurar ejercicio",
                    color = BlancoCrema
                )
            },

            text = {

                if (ejercicioSeleccionado == null) {

                    Column {
                        listaEjercicios.forEach { ejercicio ->
                            TextButton(
                                onClick = { ejercicioSeleccionado = ejercicio },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = ejercicio.getNombre(), color = BlancoCrema)
                            }
                        }
                    }

                } else {

                    Column {

                        GymAnalyticsTitulo(
                            titulo = ejercicioSeleccionado!!.getNombre(),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        SelectorEsquemaSeries(
                            esquemas = esquemasSeries,
                            seleccionado = esquemaSeriesSeleccionado,
                            onSeleccionar = { esquemaSeriesSeleccionado = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        SelectorEsquemaReps(
                            esquemas = esquemasReps,
                            seleccionado = esquemaRepsSeleccionado,
                            onSeleccionar = { esquemaRepsSeleccionado = it }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        GymAnalyticsOutlinedInput(
                            value = peso,
                            onValueChange = { peso = it },
                            label = "Peso",
                            keyboardType = KeyboardType.Decimal
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        GymAnalyticsOutlinedInput(
                            value = descanso,
                            onValueChange = { descanso = it },
                            label = "Descanso (segundos)",
                            keyboardType = KeyboardType.Number
                        )
                    }
                }
            },

            confirmButton = {

                if (ejercicioSeleccionado == null) {

                    TextButton(onClick = { diaAgregandoEjercicio = null }) {
                        Text(text = "CERRAR", color = BlancoCrema)
                    }

                } else {

                    TextButton(
                        onClick = {

                            val dia = diaAgregandoEjercicio
                            val ejercicio = ejercicioSeleccionado
                            val esquemaSeries = esquemaSeriesSeleccionado
                            val esquemaReps = esquemaRepsSeleccionado

                            if (dia != null && ejercicio != null && esquemaSeries != null && esquemaReps != null) {

                                viewModel.agregarEjercicio(
                                    dia = dia,
                                    ejercicio = ejercicio,
                                    esquemaSeries = esquemaSeries,
                                    esquemaReps = esquemaReps,
                                    peso = peso.toDoubleOrNull(),
                                    descansoSegundos = descanso.toIntOrNull() ?: 0
                                )

                                cerrarDialogo()
                            }
                        }
                    ) {
                        Text(text = "AGREGAR", color = RojoOscuro)
                    }
                }
            },

            dismissButton = {

                if (ejercicioSeleccionado != null) {

                    TextButton(
                        onClick = {
                            ejercicioSeleccionado = null
                            esquemaSeriesSeleccionado = null
                            esquemaRepsSeleccionado = null
                            peso = ""
                            descanso = ""
                        }
                    ) {
                        Text(text = "VOLVER", color = BlancoCrema)
                    }
                }
            }
        )
    }
}

fun nombreDia(dia: DiaSemana): String {
    return when (dia) {
        DiaSemana.LUNES -> "LUNES"
        DiaSemana.MARTES -> "MARTES"
        DiaSemana.MIERCOLES -> "MIÉRCOLES"
        DiaSemana.JUEVES -> "JUEVES"
        DiaSemana.VIERNES -> "VIERNES"
        DiaSemana.SABADO -> "SÁBADO"
        DiaSemana.DOMINGO -> "DOMINGO"
    }
}