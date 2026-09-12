package com.kotlin.kotlingymanalytics.data.models

import java.time.LocalDate

data class Ciclo(
    private var id: String,
    // private val usuarioId: String,
    private var fechaInicio: LocalDate,
    private var cantidadSemanas: Int,
    private var repetible: Boolean = false,
    private var rutinasPorSemana: List<Rutina>
) {
    val fechaTermino: LocalDate
        get() = fechaInicio.plusWeeks(cantidadSemanas.toLong())

    fun getId(): String {
        return id
    }

    fun setId(id: String) {
        this.id = id
    }

    fun getFechaInicio(): LocalDate {
        return fechaInicio
    }

    fun setFechaInicio(fechaInicio: LocalDate) {
        this.fechaInicio = fechaInicio
    }

    fun getCantidadSemanas(): Int {
        return cantidadSemanas
    }

    fun setCantidadSemanas(cantidadSemanas: Int) {
        this.cantidadSemanas = cantidadSemanas
    }

    fun getRepetible(): Boolean {
        return repetible
    }

    fun setRepetible(repetible: Boolean) {
        this.repetible = repetible
    }

    fun getRutinasPorSemana(): List<Rutina> {
        return rutinasPorSemana
    }

    fun setRutinasPorSemana(rutinasPorSemana: List<Rutina>) {
        this.rutinasPorSemana = rutinasPorSemana
    }
}