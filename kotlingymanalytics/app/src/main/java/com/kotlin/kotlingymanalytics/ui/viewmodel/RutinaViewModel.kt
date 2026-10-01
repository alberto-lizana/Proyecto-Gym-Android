package com.kotlin.kotlingymanalytics.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.data.models.EjercicioBorrador
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.room.entity.EjercicioAsignadoEntity
import com.kotlin.kotlingymanalytics.room.entity.EjercicioEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaRepsEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaSeriesEntity
import com.kotlin.kotlingymanalytics.room.entity.GymDatabase
import com.kotlin.kotlingymanalytics.room.entity.RutinaEntity
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RutinaViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GymDatabase.getInstance(application).gymDao()

    // Catálogo leído desde Room
    val ejerciciosDisponibles = dao.ejercicios()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val esquemasReps = dao.esquemasReps()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val esquemasSeries = dao.esquemasSeries()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Borrador de la rutina
    var nombreRutina by mutableStateOf("")
        private set

    var diasSeleccionados by mutableStateOf(setOf<DiaSemana>())
        private set

    var ejerciciosPorDia by mutableStateOf<Map<DiaSemana, List<EjercicioBorrador>>>(emptyMap())
        private set

    fun configurarRutina(nombre: String, dias: Set<DiaSemana>) {
        nombreRutina = nombre
        diasSeleccionados = dias
    }

    fun agregarEjercicio(
        dia: DiaSemana,
        ejercicio: EjercicioEntity,
        esquemaSeries: EsquemaSeriesEntity,
        esquemaReps: EsquemaRepsEntity,
        peso: Double?,
        descansoSegundos: Int
    ) {
        val nuevo = EjercicioBorrador(ejercicio, esquemaReps, esquemaSeries, peso, descansoSegundos)
        ejerciciosPorDia = ejerciciosPorDia +
                (dia to (ejerciciosPorDia[dia].orEmpty() + nuevo))
    }

    fun guardarRutina(
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val usuario = SessionManager.usuarioActual.value
        if (usuario == null) {
            onError()
            return
        }

        viewModelScope.launch {
            try {
                val asignados = ejerciciosPorDia.flatMap { (dia, lista) ->
                    lista.mapIndexed { indice, b ->
                        EjercicioAsignadoEntity(
                            rutinaId = 0,
                            dia = dia,
                            orden = indice,
                            ejercicioId = b.ejercicio.id,
                            esquemaRepsId = b.esquemaReps.id,
                            esquemaSeriesId = b.esquemaSeries.id,
                            peso = b.peso,
                            descansoSegundos = b.descansoSegundos
                        )
                    }
                }
                dao.crearRutina(RutinaEntity(usuarioId = usuario.id, nombre = nombreRutina), asignados)
                onSuccess()
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun limpiarBorrador() {
        nombreRutina = ""
        diasSeleccionados = emptySet()
        ejerciciosPorDia = emptyMap()
    }
}