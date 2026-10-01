package com.kotlin.kotlingymanalytics.core.utils

import com.kotlin.kotlingymanalytics.data.enums.DiaSemana
import com.kotlin.kotlingymanalytics.room.entity.EsquemaRepsEntity
import java.time.DayOfWeek
import java.time.LocalDate

fun formatoRepeticiones(
    esquema: EsquemaRepsEntity
): String {
    return if (esquema.repeticionesMax == null) {
        esquema.repeticionesMin.toString()
    } else {
        "${esquema.repeticionesMin}–${esquema.repeticionesMax}"
    }
}

fun diaHoy(hoy: LocalDate): DiaSemana =
    when (hoy.dayOfWeek) {
        DayOfWeek.MONDAY -> DiaSemana.LUNES
        DayOfWeek.TUESDAY -> DiaSemana.MARTES
        DayOfWeek.WEDNESDAY -> DiaSemana.MIERCOLES
        DayOfWeek.THURSDAY -> DiaSemana.JUEVES
        DayOfWeek.FRIDAY -> DiaSemana.VIERNES
        DayOfWeek.SATURDAY -> DiaSemana.SABADO
        DayOfWeek.SUNDAY -> DiaSemana.DOMINGO
    }

fun nombreDia(dia: DiaSemana): String {
    return when (dia) {
        DiaSemana.LUNES -> "Lunes"
        DiaSemana.MARTES -> "Martes"
        DiaSemana.MIERCOLES -> "Miércoles"
        DiaSemana.JUEVES -> "Jueves"
        DiaSemana.VIERNES -> "Viernes"
        DiaSemana.SABADO -> "Sábado"
        DiaSemana.DOMINGO -> "Domingo"
    }
}

