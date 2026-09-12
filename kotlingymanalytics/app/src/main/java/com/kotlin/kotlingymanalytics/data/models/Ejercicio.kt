package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular

data class Ejercicio(

    private var id: Long,
    private var nombre: String,
    private var grupoMuscularPrincipal: GrupoMuscular,
    private var musculosSecundarios: List<GrupoMuscular> = emptyList()

){
    fun getId(): Long {
        return id
    }

    fun setId(id: Long) {
        this.id = id
    }

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getGrupoMuscularPrincipal(): GrupoMuscular {
        return grupoMuscularPrincipal
    }

    fun setGrupoMuscularPrincipal(grupoMuscularPrincipal: GrupoMuscular) {
        this.grupoMuscularPrincipal = grupoMuscularPrincipal
    }

    fun getMusculosSecundarios(): List<GrupoMuscular> {
        return musculosSecundarios
    }

    fun setMusculosSecundarios(musculosSecundarios: List<GrupoMuscular>) {
        this.musculosSecundarios = musculosSecundarios
    }

}