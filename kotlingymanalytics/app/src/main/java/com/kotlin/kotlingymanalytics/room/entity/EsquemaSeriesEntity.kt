package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "esquema_series")
data class EsquemaSeriesEntity(
    @PrimaryKey val id: Long,
    val numeroSeries: Int
)
