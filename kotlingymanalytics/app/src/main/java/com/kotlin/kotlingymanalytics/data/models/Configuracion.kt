package com.kotlin.kotlingymanalytics.data.models

data class Configuracion(

    private var esquemaReps: EsquemaReps,
    private var esquemaSeries: EsquemaSeries,
    private var peso: Double? = null,
    private var rir: Int? = null,
    private var rpe: Double? = null,
    private var descansoSegundos: Int

){
    fun getEsquemaReps(): EsquemaReps {
        return esquemaReps
    }

    fun setEsquemaReps(esquemaReps: EsquemaReps) {
        this.esquemaReps = esquemaReps
    }

    fun getEsquemaSeries(): EsquemaSeries {
        return esquemaSeries
    }

    fun setEsquemaSeries(esquemaSeries: EsquemaSeries) {
        this.esquemaSeries = esquemaSeries
    }

    fun getPeso(): Double? {
        return peso
    }

    fun setPeso(peso: Double?) {
        this.peso = peso
    }

    fun getRir(): Int? {
        return rir
    }

    fun setRir(rir: Int?) {
        this.rir = rir
    }

    fun getRpe(): Double? {
        return rpe
    }

    fun setRpe(rpe: Double?) {
        this.rpe = rpe
    }

    fun getDescansoSegundos(): Int {
        return descansoSegundos
    }

    fun setDescansoSegundos(descansoSegundos: Int) {
        this.descansoSegundos = descansoSegundos
    }
}