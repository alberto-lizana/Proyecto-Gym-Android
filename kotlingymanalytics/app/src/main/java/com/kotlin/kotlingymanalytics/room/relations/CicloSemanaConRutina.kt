package com.kotlin.kotlingymanalytics.room.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.kotlin.kotlingymanalytics.room.entity.CicloSemanaEntity
import com.kotlin.kotlingymanalytics.room.entity.RutinaEntity

data class CicloSemanaConRutina(
    @Embedded val semana: CicloSemanaEntity,
    @Relation(entity = RutinaEntity::class, parentColumn = "rutinaId", entityColumn = "id")
    val rutina: RutinaCompleta
)
