package com.kotlin.kotlingymanalytics.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.kotlin.kotlingymanalytics.room.entity.CicloEntity
import com.kotlin.kotlingymanalytics.room.entity.CicloSemanaEntity

data class CicloCompleto(
    @Embedded val ciclo: CicloEntity,
    @Relation(entity = CicloSemanaEntity::class, parentColumn = "id", entityColumn = "cicloId")
    val semanas: List<CicloSemanaConRutina>
)
