package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.core.utils.SoundManager
import com.kotlin.kotlingymanalytics.core.utils.vibrarError
import com.kotlin.kotlingymanalytics.data.enums.AlertTipo
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsAlert
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsOutlinedInput
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro
import com.kotlin.kotlingymanalytics.ui.viewmodel.CicloViewModel
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymAnalyticsCrearCiclo(
    onCicloCreado: () -> Unit,
    viewModel: CicloViewModel
) {
    val context = LocalContext.current
    val rutinas by viewModel.rutinas.collectAsState()
    val formato = remember { DateTimeFormatter.ofPattern("dd/MM/yyyy") }

    var nombreCiclo by remember { mutableStateOf("") }

    // Alerta de guardado
    var mostrarAlert by remember { mutableStateOf(false) }
    var tipoAlert by remember { mutableStateOf(AlertTipo.ERROR) }
    var mensajeAlert by remember { mutableStateOf("") }

    // Estado local de los diálogos
    var mostrarFecha by remember { mutableStateOf(false) }
    var semanaEditando by remember { mutableStateOf<Int?>(null) }

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

            GymAnalyticsTitulo(titulo = "Crear ciclo")

            Spacer(modifier = Modifier.height(12.dp))

            GymAnalyticsOutlinedInput(
                value = nombreCiclo,
                onValueChange = { nombreCiclo = it },
                label = "Nombre de Ciclo",
                keyboardType = KeyboardType.Text
            )


            // Fecha de inicio
            GymAnalyticsCard(titulo = "Fecha de inicio") {
                Text(
                    text = viewModel.fechaInicio.format(formato),
                    color = BlancoCrema,
                    style = MaterialTheme.typography.bodyLarge
                )
                TextButton(
                    onClick = { mostrarFecha = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "CAMBIAR FECHA", color = RojoOscuro)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Duración
            GymAnalyticsCard(titulo = "Duración") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        onClick = { viewModel.cambiarCantidadSemanas(viewModel.cantidadSemanas - 1) }
                    ) {
                        Text(text = "−", color = BlancoCrema, style = MaterialTheme.typography.titleLarge)
                    }

                    Text(
                        text = "${viewModel.cantidadSemanas} semanas",
                        color = BlancoCrema,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )

                    TextButton(
                        onClick = { viewModel.cambiarCantidadSemanas(viewModel.cantidadSemanas + 1) }
                    ) {
                        Text(text = "+", color = BlancoCrema, style = MaterialTheme.typography.titleLarge)
                    }
                }

                Text(
                    text = "Termina: " +
                            viewModel.fechaInicio.plusWeeks(viewModel.cantidadSemanas.toLong()).format(formato),
                    color = BlancoCrema,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Opciones
            GymAnalyticsCard(titulo = "Opciones") {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Repetir al terminar", color = BlancoCrema)
                    Switch(
                        checked = viewModel.repetible,
                        onCheckedChange = { viewModel.cambiarRepetible(it) }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Activar al guardar", color = BlancoCrema)
                    Switch(
                        checked = viewModel.activar,
                        onCheckedChange = { viewModel.cambiarActivar(it) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Una tarjeta por semana, con la rutina elegida
            viewModel.rutinasPorSemana.forEachIndexed { indice, rutina ->

                GymAnalyticsCard(titulo = "Semana ${indice + 1}") {
                    TextButton(
                        onClick = { semanaEditando = indice },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = rutina?.nombre ?: "Seleccionar rutina",
                            color = if (rutina == null) RojoOscuro else BlancoCrema
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            GymAnalyticsButton(
                text = "GUARDAR CICLO",
                containerColor = RojoOscuro,
                onClick = {
                    viewModel.guardarCiclo(
                        nombreCiclo,
                        onSuccess = {
                            SoundManager.reproducirExito()
                            tipoAlert = AlertTipo.EXITO
                            mensajeAlert = "Ciclo guardado"
                            mostrarAlert = true
                        },
                        onError = {
                            SoundManager.reproducirError()
                            context.vibrarError()
                            tipoAlert = AlertTipo.ERROR
                            mensajeAlert = "No se pudo guardar el ciclo"
                            mostrarAlert = true
                        }
                    )
                },
                enabled = viewModel.puedeGuardar,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (mostrarAlert) {
            GymAnalyticsAlert(
                tipo = tipoAlert,
                mensaje = mensajeAlert,
                onDismiss = {
                    mostrarAlert = false
                    if (tipoAlert == AlertTipo.EXITO) {
                        onCicloCreado()
                    }
                }
            )
        }
    }

    // Elegir fecha de inicio
    if (mostrarFecha) {
        val pickerState = rememberDatePickerState(
            initialSelectedDateMillis = viewModel.fechaInicio
                .atStartOfDay(ZoneOffset.UTC)
                .toInstant()
                .toEpochMilli()
        )

        DatePickerDialog(
            onDismissRequest = { mostrarFecha = false },
            confirmButton = {
                TextButton(
                    onClick = {
                        pickerState.selectedDateMillis?.let { millis ->
                            viewModel.cambiarFecha(
                                Instant.ofEpochMilli(millis).atZone(ZoneOffset.UTC).toLocalDate()
                            )
                        }
                        mostrarFecha = false
                    }
                ) {
                    Text(text = "ACEPTAR", color = RojoOscuro)
                }
            },
            dismissButton = {
                TextButton(onClick = { mostrarFecha = false }) {
                    Text(text = "CANCELAR", color = BlancoCrema)
                }
            }
        ) {
            DatePicker(state = pickerState)
        }
    }

    // Elegir la rutina de una semana
    semanaEditando?.let { semana ->
        AlertDialog(
            onDismissRequest = { semanaEditando = null },

            title = {
                Text(text = "Rutina para la semana ${semana + 1}", color = BlancoCrema)
            },

            text = {
                if (rutinas.isEmpty()) {
                    Text(
                        text = "Aún no tienes rutinas. Crea una primero.",
                        color = BlancoCrema
                    )
                } else {
                    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                        rutinas.forEach { rutina ->
                            TextButton(
                                onClick = {
                                    viewModel.asignarRutina(semana, rutina)
                                    semanaEditando = null
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(text = rutina.nombre, color = BlancoCrema)
                            }
                        }
                    }
                }
            },

            confirmButton = {
                TextButton(onClick = { semanaEditando = null }) {
                    Text(text = "CERRAR", color = BlancoCrema)
                }
            }
        )
    }
}