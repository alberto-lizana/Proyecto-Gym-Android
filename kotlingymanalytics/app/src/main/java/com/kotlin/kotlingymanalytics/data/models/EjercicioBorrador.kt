package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.room.entity.EjercicioEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaRepsEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaSeriesEntity

data class EjercicioBorrador(
    val ejercicio: EjercicioEntity,
    val esquemaReps: EsquemaRepsEntity,
    val esquemaSeries: EsquemaSeriesEntity,
    val peso: Double?,
    val descansoSegundos: Int
)
