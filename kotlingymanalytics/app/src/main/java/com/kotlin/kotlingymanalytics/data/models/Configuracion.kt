package com.kotlin.kotlingymanalytics.data.models

class Configuracion(
    esquemaReps: EsquemaReps,
    esquemaSeries: EsquemaSeries,
    peso: Double? = null,
    rir: Int? = null,
    rpe: Double? = null,
    descansoSegundos: Int
) {

    var esquemaReps = esquemaReps
        private set

    var esquemaSeries = esquemaSeries
        private set

    var peso = peso
        private set

    var rir = rir
        private set

    var rpe = rpe
        private set

    var descansoSegundos = descansoSegundos
        private set
}