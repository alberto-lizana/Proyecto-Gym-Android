package com.kotlin.kotlingymanalytics.data.models

import java.time.LocalDate

class Ciclo(
    private val id: String,
    fechaInicio: LocalDate,
    cantidadSemanas: Int,
    repetible: Boolean = false,
    rutinasPorSemana: List<Rutina>
) {
    var fechaInicio = fechaInicio
        private set

    var cantidadSemanas = cantidadSemanas
        private set

    var repetible = repetible
        private set

    var rutinasPorSemana = rutinasPorSemana
        private set

    private val fechaTermino: LocalDate
        get() = fechaInicio.plusWeeks(cantidadSemanas.toLong())
}