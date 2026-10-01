package com.kotlin.kotlingymanalytics.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.kotlin.kotlingymanalytics.room.entity.EjercicioAsignadoEntity
import com.kotlin.kotlingymanalytics.room.entity.RutinaEntity

data class RutinaCompleta(
    @Embedded val rutina: RutinaEntity,
    @Relation(entity = EjercicioAsignadoEntity::class, parentColumn = "id", entityColumn = "rutinaId")
    val ejercicios: List<EjercicioAsignadoConDetalle>
) {
    val ejerciciosPorDia get() = ejercicios
        .sortedBy { it.asignado.orden }
        .groupBy { it.asignado.dia }
}