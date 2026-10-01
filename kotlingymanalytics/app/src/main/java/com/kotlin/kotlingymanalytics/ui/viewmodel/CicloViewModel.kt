package com.kotlin.kotlingymanalytics.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.kotlingymanalytics.core.utils.diaHoy
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.room.entity.CicloEntity
import com.kotlin.kotlingymanalytics.room.entity.GymDatabase
import com.kotlin.kotlingymanalytics.room.entity.RutinaEntity
import com.kotlin.kotlingymanalytics.room.relations.CicloCompleto
import com.kotlin.kotlingymanalytics.room.relations.RutinaCompleta
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class CicloViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = GymDatabase.getInstance(application).gymDao()

    @OptIn(ExperimentalCoroutinesApi::class)
    val rutinas = SessionManager.usuarioActual
        .flatMapLatest { usuario ->
            if (usuario == null) flowOf(emptyList<RutinaEntity>())
            else dao.rutinasDeUsuario(usuario.id)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    @OptIn(ExperimentalCoroutinesApi::class)
    val cicloActivo = SessionManager.usuarioActual
        .flatMapLatest { usuario ->
            if (usuario == null) {
                flowOf(null)
            } else {
                dao.cicloActivo(usuario.id)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val ciclos = SessionManager.usuarioActual
        .flatMapLatest { usuario ->
            if (usuario == null) {
                flowOf(emptyList<CicloCompleto>())
            } else {
                dao.ciclosDeUsuario(usuario.id)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    @OptIn(ExperimentalCoroutinesApi::class)
    val rutinasCompletas = SessionManager.usuarioActual
        .flatMapLatest { usuario ->
            if (usuario == null) {
                flowOf(emptyList<RutinaCompleta>())
            } else {
                dao.rutinasCompletasDeUsuario(usuario.id)
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    // Borrador del ciclo
    var fechaInicio by mutableStateOf(LocalDate.now())
        private set

    var cantidadSemanas by mutableStateOf(4)
        private set

    var repetible by mutableStateOf(false)
        private set

    var activar by mutableStateOf(true)
        private set

    // La posición en la lista es el número de semana (0 = semana 1)
    var rutinasPorSemana by mutableStateOf<List<RutinaEntity?>>(List(4) { null })
        private set

    val puedeGuardar: Boolean
        get() = rutinasPorSemana.isNotEmpty() && rutinasPorSemana.all { it != null }

    fun cambiarFecha(fecha: LocalDate) {
        fechaInicio = fecha
    }

    fun cambiarCantidadSemanas(nueva: Int) {
        val n = nueva.coerceIn(1, 52)
        cantidadSemanas = n
        // Conserva las rutinas ya elegidas y agrega o quita huecos al final
        rutinasPorSemana = List(n) { i -> rutinasPorSemana.getOrNull(i) }
    }

    fun asignarRutina(semana: Int, rutina: RutinaEntity) {
        rutinasPorSemana = rutinasPorSemana.toMutableList().also { it[semana] = rutina }
    }

    fun cambiarRepetible(valor: Boolean) {
        repetible = valor
    }

    fun cambiarActivar(valor: Boolean) {
        activar = valor
    }

    fun guardarCiclo(
        nombreCiclo : String,
        onSuccess: () -> Unit,
        onError: () -> Unit
    ) {
        val usuario = SessionManager.usuarioActual.value
        val rutinaIds = rutinasPorSemana.map { it?.id }

        if (usuario == null || rutinaIds.any { it == null }) {
            onError()
            return
        }

        viewModelScope.launch {
            try {
                dao.crearCiclo(
                    ciclo = CicloEntity(
                        nombre = nombreCiclo,
                        usuarioId = usuario.id,
                        fechaInicio = fechaInicio,
                        cantidadSemanas = cantidadSemanas,
                        repetible = repetible
                    ),
                    rutinaIdsPorSemana = rutinaIds.filterNotNull(),
                    activar = activar
                )
                onSuccess()
            } catch (e: Exception) {
                onError()
            }
        }
    }

    fun obtenerDiaHoy(hoy: LocalDate): DiaSemana {
        return diaHoy(hoy)
    }

    fun limpiarBorrador() {
        fechaInicio = LocalDate.now()
        cantidadSemanas = 4
        repetible = false
        activar = true
        rutinasPorSemana = List(4) { null }
    }
}