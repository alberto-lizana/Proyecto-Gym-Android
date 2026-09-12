package com.kotlin.kotlingymanalytics.data.models

data class EsquemaSeries(

    private var id: Long,
    private var numeroSeries: Int

) {

    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getNumeroSeries(): Int {
        return numeroSeries
    }

    fun setNumeroSeries(numeroSeries: Int) {
        this.numeroSeries = numeroSeries
    }
}