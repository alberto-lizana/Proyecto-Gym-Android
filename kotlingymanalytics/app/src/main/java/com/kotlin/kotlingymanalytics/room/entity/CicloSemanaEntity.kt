package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "ciclo_semana",
    primaryKeys = ["cicloId", "numeroSemana"],
    foreignKeys = [
        ForeignKey(CicloEntity::class, ["id"], ["cicloId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(RutinaEntity::class, ["id"], ["rutinaId"], onDelete = ForeignKey.RESTRICT)
    ],
    indices = [Index("rutinaId")]
)
data class CicloSemanaEntity(
    val cicloId: Long,
    val numeroSemana: Int, // Inicial 0
    val rutinaId: Long
)
