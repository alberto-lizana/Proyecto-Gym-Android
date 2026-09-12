package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.DiaSemana

data class Rutina(

    // private var id: Long,
    // private var usuarioId: String,
    private var nombre: String,
    private var ejerciciosPorDia: Map<DiaSemana, List<EjercicioAsignado>>

) {

    fun getNombre(): String {
        return nombre
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun getEjerciciosPorDia(): Map<DiaSemana, List<EjercicioAsignado>> {
        return ejerciciosPorDia
    }

    fun setEjerciciosPorDia(
        ejerciciosPorDia: Map<DiaSemana, List<EjercicioAsignado>>
    ) {
        this.ejerciciosPorDia = ejerciciosPorDia
    }
}