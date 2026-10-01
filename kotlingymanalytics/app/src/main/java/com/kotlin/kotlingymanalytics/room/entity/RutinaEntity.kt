package com.kotlin.kotlingymanalytics.room.entity
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "rutina",
    indices = [
        Index("usuarioId"),
        Index(
            value = ["usuarioId", "nombre"],
            unique = true
        )
    ]
)
data class RutinaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val usuarioId: Long,
    val nombre: String
)