package com.kotlin.kotlingymanalytics.core.utils

val epley = { peso: Double, repeticiones: Int -> peso * (1 + repeticiones / 30.0) }
val brzycki = { peso: Double, repeticiones: Int -> peso * (36.0 / (37 - repeticiones)) }

val lander = { peso: Double, repeticiones: Int -> (100 * peso) / (101.3 - 2.67123 * repeticiones) }
val oConner = { peso: Double, repeticiones: Int -> peso * (1 + 0.025 * repeticiones) }

fun calcularRmFuncionSuperior(
    peso: Double,
    repeticiones: Int,
    function: (Double, Int) -> Double
): Double {
    return function(peso, repeticiones)
}