package com.kotlin.kotlingymanalytics.room.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular

@Entity(tableName = "ejercicio")
data class EjercicioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val grupoMuscularPrincipal: GrupoMuscular
)