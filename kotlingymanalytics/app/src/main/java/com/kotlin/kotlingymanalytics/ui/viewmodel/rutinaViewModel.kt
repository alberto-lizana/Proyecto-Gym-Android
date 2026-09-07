package com.kotlin.kotlingymanalytics.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.data.models.Configuracion
import com.kotlin.kotlingymanalytics.data.models.Ejercicio
import com.kotlin.kotlingymanalytics.data.models.EjercicioAsignado
import com.kotlin.kotlingymanalytics.data.models.EsquemaReps
import com.kotlin.kotlingymanalytics.data.models.EsquemaSeries

class rutinaViewModel : ViewModel() {

    var nombreRutina by mutableStateOf("")
        private set

    var diasSeleccionados by mutableStateOf(setOf<DiaSemana>())
        private set

    var ejerciciosPorDia by mutableStateOf<Map<DiaSemana, List<EjercicioAsignado>>>(emptyMap())
        private set

    fun configurarRutina(
        nombre: String,
        dias: Set<DiaSemana>
    ) {
        nombreRutina = nombre
        diasSeleccionados = dias
    }

    fun agregarEjercicio(
        dia: DiaSemana,
        ejercicio: Ejercicio,
        esquemaSeries: EsquemaSeries,
        esquemaReps: EsquemaReps,
        peso: Double?,
        descansoSegundos: Int
    ) {
        val configuracion = Configuracion(
            esquemaReps = esquemaReps,
            esquemaSeries = esquemaSeries,
            peso = peso,
            descansoSegundos = descansoSegundos
        )

        val ejercicioAsignado = EjercicioAsignado(
            ejercicio = ejercicio,
            configuracion = configuracion
        )

        ejerciciosPorDia = ejerciciosPorDia +
                (dia to (ejerciciosPorDia[dia].orEmpty() + ejercicioAsignado))
    }
}

