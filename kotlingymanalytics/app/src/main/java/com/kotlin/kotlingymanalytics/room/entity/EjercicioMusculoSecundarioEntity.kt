package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular

@Entity(
    tableName = "ejercicio_musculo_secundario",
    primaryKeys = ["ejercicioId", "grupoMuscular"],
    foreignKeys = [ForeignKey(EjercicioEntity::class, ["id"], ["ejercicioId"], onDelete = ForeignKey.CASCADE)]
)
data class EjercicioMusculoSecundarioEntity(
    val ejercicioId: Long,
    val grupoMuscular: GrupoMuscular
)
