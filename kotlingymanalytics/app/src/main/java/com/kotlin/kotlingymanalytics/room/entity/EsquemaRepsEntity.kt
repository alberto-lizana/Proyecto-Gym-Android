package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "esquema_reps")
data class EsquemaRepsEntity(
    @PrimaryKey val id: Long,
    val repeticionesMin: Int,
    val repeticionesMax: Int? = null
)
