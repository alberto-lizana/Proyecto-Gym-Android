package com.kotlin.kotlingymanalytics.data.models

import com.kotlin.kotlingymanalytics.data.enums.DiaSemana

class Rutina(
    // id: Long,
    // usuarioId: String,
    nombre: String,
    ejerciciosPorDia: Map<DiaSemana, List<EjercicioAsignado>>
) {
    var nombre = nombre
        private set

    var ejerciciosPorDia = ejerciciosPorDia
        private set
}