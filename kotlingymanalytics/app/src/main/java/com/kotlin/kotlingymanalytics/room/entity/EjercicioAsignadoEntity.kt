package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kotlin.kotlingymanalytics.data.enums.DiaSemana

@Entity(
    tableName = "ejercicio_asignado",
    foreignKeys = [
        ForeignKey(RutinaEntity::class, ["id"], ["rutinaId"], onDelete = ForeignKey.CASCADE),
        ForeignKey(EjercicioEntity::class, ["id"], ["ejercicioId"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(EsquemaRepsEntity::class, ["id"], ["esquemaRepsId"], onDelete = ForeignKey.RESTRICT),
        ForeignKey(EsquemaSeriesEntity::class, ["id"], ["esquemaSeriesId"], onDelete = ForeignKey.RESTRICT)
    ],
    indices = [Index("rutinaId"), Index("ejercicioId"), Index("esquemaRepsId"), Index("esquemaSeriesId")]
)
data class EjercicioAsignadoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val rutinaId: Long,
    val dia: DiaSemana,
    val orden: Int,
    val ejercicioId: Long,

    // Configuracion
    val esquemaRepsId: Long,
    val esquemaSeriesId: Long,
    val peso: Double? = null,
    val rir: Int? = null,
    val rpe: Double? = null,
    val descansoSegundos: Int
)
