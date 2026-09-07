package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.DiaSemana

data class Rutina(

    // val id: Long,
    // val usuarioId: String,
    val nombre: String,
    val ejerciciosPorDia: Map<DiaSemana, List<EjercicioAsignado>>

)