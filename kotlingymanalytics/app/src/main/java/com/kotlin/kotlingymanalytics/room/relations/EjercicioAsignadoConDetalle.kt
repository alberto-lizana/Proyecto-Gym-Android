package com.kotlin.kotlingymanalytics.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.kotlin.kotlingymanalytics.room.entity.EjercicioAsignadoEntity
import com.kotlin.kotlingymanalytics.room.entity.EjercicioEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaRepsEntity
import com.kotlin.kotlingymanalytics.room.entity.EsquemaSeriesEntity

data class EjercicioAsignadoConDetalle(
    @Embedded val asignado: EjercicioAsignadoEntity,
    @Relation(parentColumn = "ejercicioId", entityColumn = "id")
    val ejercicio: EjercicioEntity,
    @Relation(parentColumn = "esquemaRepsId", entityColumn = "id")
    val esquemaReps: EsquemaRepsEntity,
    @Relation(parentColumn = "esquemaSeriesId", entityColumn = "id")
    val esquemaSeries: EsquemaSeriesEntity
)
