package com.kotlin.kotlingymanalytics.data.models

data class EjercicioAsignado(

    private var ejercicio: Ejercicio,
    private var configuracion: Configuracion

){
    fun getEjercicio(): Ejercicio {
        return ejercicio
    }

    fun setEjercicio(ejercicio: Ejercicio) {
        this.ejercicio = ejercicio
    }

    fun getConfiguracion(): Configuracion {
        return configuracion
    }

    fun setConfiguracion(configuracion: Configuracion) {
        this.configuracion = configuracion
    }
}