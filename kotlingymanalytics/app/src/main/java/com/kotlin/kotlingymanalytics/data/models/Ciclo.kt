package com.kotlin.kotlingymanalytics.data.models

import java.time.LocalDate

data class Ciclo(
    val id: String,
    // val usuarioId: String,
    val fechaInicio: LocalDate,
    val cantidadSemanas: Int,
    val repetible: Boolean = false,
    val rutinasPorSemana: List<Rutina>
) {
    val fechaTermino: LocalDate
        get() = fechaInicio.plusWeeks(cantidadSemanas.toLong())
}