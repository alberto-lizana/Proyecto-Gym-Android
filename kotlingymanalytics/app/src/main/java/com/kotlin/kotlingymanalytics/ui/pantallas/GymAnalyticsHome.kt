package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.ui.componentes.AddIcon
import com.kotlin.kotlingymanalytics.ui.componentes.CalendarIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsAccionHome
import com.kotlin.kotlingymanalytics.ui.componentes.MancuernaIcon
import com.kotlin.kotlingymanalytics.ui.componentes.DownloadIcon
import com.kotlin.kotlingymanalytics.ui.componentes.SpeedIcon
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsButton
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsCard
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsDato
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo
import com.kotlin.kotlingymanalytics.ui.componentes.ViewTimeLineIcon
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro
import com.kotlin.kotlingymanalytics.ui.viewmodel.CicloViewModel
import java.time.LocalDate
import java.time.temporal.ChronoUnit


@Composable
fun GymAnalyticsHome(
    cicloViewModel: CicloViewModel,
    toCrearRutina: () -> Unit,
    onStart: () -> Unit,
    toCalcularRm: () -> Unit,
    toCrearCiclo: () -> Unit,
    toMisCiclosRutinas: () -> Unit
) {

    val usuario = SessionManager.usuarioActual.collectAsState().value
    val cicloActivo = cicloViewModel.cicloActivo.collectAsState().value
    val hoy = LocalDate.now()
    val cicloComenzo = cicloActivo != null && hoy >= cicloActivo.ciclo.fechaInicio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        GymAnalyticsTitulo(
            titulo = "Inicio"
        )

        Spacer(modifier = Modifier.height(8.dp))


        if (cicloActivo == null) {

            // No existe ciclo
            GymAnalyticsTitulo(
                titulo = "Bienvenido, ${
                    listOfNotNull(
                        usuario?.nombre,
                        usuario?.appat,
                        usuario?.apmat
                    ).joinToString(" ")
                }",
                style = MaterialTheme.typography.titleLarge,
            )

        } else if (!cicloComenzo) {

            // Existe ciclo, pero todavía no comienza
            val diasRestantes = ChronoUnit.DAYS.between(
                hoy,
                cicloActivo.ciclo.fechaInicio
            )

            GymAnalyticsTitulo(
                titulo = "Tu ciclo comienza en $diasRestantes días",
                style = MaterialTheme.typography.titleLarge,
                color = RojoOscuro,
                modifier = Modifier.padding(10.dp)
            )

        } else {

            val diaHoy = cicloViewModel.obtenerDiaHoy(hoy)

            val diasTranscurridos = ChronoUnit.DAYS.between(cicloActivo.ciclo.fechaInicio, hoy)
            val semanaActual = (diasTranscurridos / 7).toInt()
            val semana = cicloActivo.semanas.firstOrNull { it.semana.numeroSemana == semanaActual }
            val rutina = semana?.rutina

            val ejerciciosHoy = rutina
                ?.ejerciciosPorDia
                ?.get(diaHoy)
                ?: emptyList()

            val cantidadEjercicios = ejerciciosHoy.size
            val seriesTotales = ejerciciosHoy.sumOf { it.esquemaSeries.numeroSeries }
            val musculosPrincipales = ejerciciosHoy.groupingBy { it.ejercicio.grupoMuscularPrincipal }.eachCount()

            // Existe y ya comenzó
            GymAnalyticsTitulo(
                titulo = "Entrenamiento de hoy",
                style = MaterialTheme.typography.titleLarge
            )

            Card(
                elevation = CardDefaults.cardElevation(1.dp),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = FondoOscuro.copy(alpha = 0.55f)
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Spacer(modifier = Modifier.height(12.dp))

                    GymAnalyticsCard(
                        titulo = if (cantidadEjercicios == 0) "Descanso" else "Carga de Hoy"
                    ) {
                        /*
                        GymAnalyticsDato(
                            nombre = "Ciclo",
                            valor =
                        )
                        */

                        GymAnalyticsDato(
                            nombre = "Ejercicios",
                            valor = cantidadEjercicios.toString()
                        )

                        GymAnalyticsDato(
                            nombre = "Series totales",
                            valor = seriesTotales.toString()
                        )

                        GymAnalyticsDato(
                            nombre = "Músculos principales",
                            valor = musculosPrincipales.entries.joinToString(" · ") {
                                "${it.key}".lowercase()
                            }
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        GymAnalyticsButton(
                            text = "COMENZAR ENTRENAMIENTO",
                            containerColor = RojoOscuro,
                            onClick = onStart,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        GymAnalyticsTitulo(
            titulo = "Acciones",
            style = MaterialTheme.typography.titleLarge
        )

        Card(
            elevation = CardDefaults.cardElevation(1.dp),
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = FondoOscuro.copy(alpha = 0.55f)
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {

                GymAnalyticsAccionHome(
                    titulo = "Crear Ciclo",
                    subtitulo = "Crea Cilo (Conjunto de rutinas en un tiempo definido)",
                    icono = {
                        ViewTimeLineIcon()
                    },
                    onClick = { toCrearCiclo() }
                )

                GymAnalyticsAccionHome(
                    titulo = "Crear rutina",
                    subtitulo = "Crea una nueva rutina",
                    icono = {
                        AddIcon()
                    },
                    onClick = { toCrearRutina() }
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsAccionHome(
                    titulo = "Mis rutinas",
                    subtitulo = "Ver y gestionar tus rutinas",
                    icono = { MancuernaIcon() },
                    onClick = {
                        toMisCiclosRutinas()
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsAccionHome(
                    titulo = "Calendario",
                    subtitulo = "Consulta tus entrenamientos programados",
                    icono = {
                        CalendarIcon()
                    },
                    onClick = {
                        // GymAnalyticsCalendario
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                GymAnalyticsAccionHome(
                    titulo = "Calcular Rm",
                    subtitulo = "Calcula tu repetición máxima estimada",
                    icono = {
                        SpeedIcon()
                    },
                    onClick = {
                        toCalcularRm()
                    }
                )

                GymAnalyticsAccionHome(
                    titulo = "Exportar datos",
                    subtitulo = "Descarga tus rutinas y progreso",
                    icono = {
                        DownloadIcon()
                    },
                    onClick = {
                        // GymAnalyticsExportar
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
