package com.kotlin.kotlingymanalytics.data.models

class EsquemaReps(
    private val id: Long,
    repeticionesMin: Int,
    repeticionesMax: Int? = null
) {

    var repeticionesMin = repeticionesMin
        private set

    var repeticionesMax = repeticionesMax
        private set
}