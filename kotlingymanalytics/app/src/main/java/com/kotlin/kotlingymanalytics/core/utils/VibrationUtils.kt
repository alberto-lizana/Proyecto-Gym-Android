package com.kotlin.kotlingymanalytics.core.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

fun Context.vibrar(
    duracionMs: Long = 200,
    amplitud: Int = VibrationEffect.DEFAULT_AMPLITUDE
) {
    val vibrator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        val vibratorManager =
            getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
        vibratorManager.defaultVibrator
    } else {
        @Suppress("DEPRECATION")
        getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    }

    if (vibrator.hasVibrator()) {
        vibrator.vibrate(VibrationEffect.createOneShot(duracionMs, amplitud))
    }
}

fun Context.vibrarError() = vibrar(duracionMs = 200)