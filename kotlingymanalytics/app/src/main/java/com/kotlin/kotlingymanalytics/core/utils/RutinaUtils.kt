package com.kotlin.kotlingymanalytics.core.utils

import com.kotlin.kotlingymanalytics.data.enums.GrupoMuscular
import com.kotlin.kotlingymanalytics.data.models.Ejercicio
import com.kotlin.kotlingymanalytics.data.models.EsquemaReps
import com.kotlin.kotlingymanalytics.data.models.EsquemaSeries


val ejerciciosBase: Array<Ejercicio> =

    arrayOf(

        Ejercicio(
            id = 1,
            nombre = "Press banca",
            grupoMuscularPrincipal = GrupoMuscular.PECHO,
            musculosSecundarios = listOf(
                GrupoMuscular.TRICEPS,
                GrupoMuscular.HOMBRO
            )
        ),

        Ejercicio(
            id = 2,
            nombre = "Press inclinado con mancuernas",
            grupoMuscularPrincipal = GrupoMuscular.PECHO,
            musculosSecundarios = listOf(
                GrupoMuscular.TRICEPS,
                GrupoMuscular.HOMBRO
            )
        ),

        Ejercicio(
            id = 3,
            nombre = "Dominadas",
            grupoMuscularPrincipal = GrupoMuscular.ESPALDA,
            musculosSecundarios = listOf(
                GrupoMuscular.BICEPS
            )
        ),

        Ejercicio(
            id = 4,
            nombre = "Remo con barra",
            grupoMuscularPrincipal = GrupoMuscular.ESPALDA,
            musculosSecundarios = listOf(
                GrupoMuscular.BICEPS
            )
        ),

        Ejercicio(
            id = 5,
            nombre = "Press militar",
            grupoMuscularPrincipal = GrupoMuscular.HOMBRO,
            musculosSecundarios = listOf(
                GrupoMuscular.TRICEPS
            )
        ),

        Ejercicio(
            id = 6,
            nombre = "Curl de bíceps con barra",
            grupoMuscularPrincipal = GrupoMuscular.BICEPS
        ),

        Ejercicio(
            id = 7,
            nombre = "Extensión de tríceps en polea",
            grupoMuscularPrincipal = GrupoMuscular.TRICEPS
        ),

        Ejercicio(
            id = 8,
            nombre = "Sentadilla con barra",
            grupoMuscularPrincipal = GrupoMuscular.PIERNA,
            musculosSecundarios = listOf(
                GrupoMuscular.GLUTEO,
                GrupoMuscular.CORE
            )
        ),

        Ejercicio(
            id = 9,
            nombre = "Peso muerto rumano",
            grupoMuscularPrincipal = GrupoMuscular.PIERNA,
            musculosSecundarios = listOf(
                GrupoMuscular.GLUTEO
            )
        ),

        Ejercicio(
            id = 10,
            nombre = "Hip thrust",
            grupoMuscularPrincipal = GrupoMuscular.GLUTEO,
            musculosSecundarios = listOf(
                GrupoMuscular.PIERNA
            )
        ),

        Ejercicio(
            id = 11,
            nombre = "Plancha abdominal",
            grupoMuscularPrincipal = GrupoMuscular.CORE
        )
    )

val esquemaRepsBase: Array<EsquemaReps> =

    arrayOf(
        EsquemaReps(
            0L,
            repeticionesMin = 1
        ),

        EsquemaReps(
            1L,
            repeticionesMin = 2
        ),

        EsquemaReps(
            2L,
            repeticionesMin = 3
        ),

        EsquemaReps(
            3L,
            repeticionesMin = 4
        ),

        EsquemaReps(
            4L,
            repeticionesMin = 5
        ),

        EsquemaReps(
            5L,
            repeticionesMin = 6
        ),

        EsquemaReps(
            6L,
            repeticionesMin = 4,
            repeticionesMax = 6
        ),

        EsquemaReps(
            7L,
            repeticionesMin = 6,
            repeticionesMax = 8
        ),

        EsquemaReps(
            8L,
            repeticionesMin = 8,
            repeticionesMax = 10
        ),

        EsquemaReps(
            9L,
            repeticionesMin = 10,
            repeticionesMax = 12
        ),

        EsquemaReps(
            10L,
            repeticionesMin = 12,
            repeticionesMax = 15
        ),

        EsquemaReps(
            11L,
            repeticionesMin = 15,
            repeticionesMax = 20
        )
    )

fun crearEsquemaDeSeriesPredeterminados(): List<EsquemaSeries> {

    return listOf(
        EsquemaSeries(0L, numeroSeries = 1),
        EsquemaSeries(1L, numeroSeries = 2),
        EsquemaSeries(2L, numeroSeries = 3),
        EsquemaSeries(3L, numeroSeries = 4),
        EsquemaSeries(4L, numeroSeries = 5),
        EsquemaSeries(5L, numeroSeries = 6),
        EsquemaSeries(6L, numeroSeries = 7),
        EsquemaSeries(7L, numeroSeries = 8),
        EsquemaSeries(8L, numeroSeries = 9),
        EsquemaSeries(9L, numeroSeries = 10),
        EsquemaSeries(10L, numeroSeries = 11),
        EsquemaSeries(11L, numeroSeries = 12),
        EsquemaSeries(12L, numeroSeries = 13),
        EsquemaSeries(13L, numeroSeries = 14),
        EsquemaSeries(14L, numeroSeries = 15),
        EsquemaSeries(15L, numeroSeries = 16),
        EsquemaSeries(16L, numeroSeries = 17),
        EsquemaSeries(17L, numeroSeries = 18),
        EsquemaSeries(18L, numeroSeries = 19),
        EsquemaSeries(19L, numeroSeries = 20)
    )
}
