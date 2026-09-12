package com.kotlin.kotlingymanalytics.data.models

data class EsquemaReps(

    private var id: Long,
    private var repeticionesMin: Int,
    private var repeticionesMax: Int? = null

){
    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getRepeticionesMin(): Int {
        return repeticionesMin
    }

    fun setRepeticionesMin(repeticionesMin: Int) {
        this.repeticionesMin = repeticionesMin
    }

    fun getRepeticionesMax(): Int? {
        return repeticionesMax
    }

    fun setRepeticionesMax(repeticionesMax: Int?) {
        this.repeticionesMax = repeticionesMax
    }
}