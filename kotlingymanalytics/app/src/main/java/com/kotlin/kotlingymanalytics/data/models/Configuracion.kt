package com.kotlin.kotlingymanalytics.data.models

data class Configuracion(

    val esquemaReps: EsquemaReps,
    val esquemaSeries: EsquemaSeries,
    val peso: Double? = null,
    val rir: Int? = null,
    val rpe: Double? = null,
    val descansoSegundos: Int

)