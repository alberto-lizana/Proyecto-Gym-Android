package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular

class Ejercicio(
    private val  id: Long,
    nombre: String,
    grupoMuscularPrincipal: GrupoMuscular,
    musculosSecundarios: List<GrupoMuscular> = emptyList()
) {

    var nombre = nombre
        private set

    var grupoMuscularPrincipal = grupoMuscularPrincipal
        private set

    var musculosSecundarios = musculosSecundarios
        private set
}