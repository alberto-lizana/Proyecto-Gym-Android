package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "ciclo",
    indices = [Index("usuarioId")]
)
data class CicloEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val usuarioId: Long,

    val nombre: String,

    val fechaInicio: LocalDate,

    val cantidadSemanas: Int,

    val repetible: Boolean = false,

    val activo: Boolean = false
)