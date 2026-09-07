package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular

data class Ejercicio(

    val id: Long,
    val nombre: String,
    val grupoMuscularPrincipal: GrupoMuscular,
    val musculosSecundarios: List<GrupoMuscular> = emptyList()

)