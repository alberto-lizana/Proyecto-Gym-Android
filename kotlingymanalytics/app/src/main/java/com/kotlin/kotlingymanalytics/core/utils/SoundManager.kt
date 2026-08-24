package com.kotlin.kotlingymanalytics.core.utils

import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import com.kotlin.kotlingymanalytics.R

object SoundManager {
    private var soundPool: SoundPool? = null
    private var soundError: Int = 0
    private var soundExito: Int = 0

    fun init(context: Context) {
        val attrs = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(3)
            .setAudioAttributes(attrs)
            .build()

        soundError = soundPool!!.load(context, R.raw.error_sound, 1)
        soundExito = soundPool!!.load(context, R.raw.exito_sound, 1)
    }

    fun reproducirError() {
        soundPool?.play(soundError, 1f, 1f, 0, 0, 1f)
    }

    fun reproducirExito() {
        soundPool?.play(soundExito, 1f, 1f, 0, 0, 1f)
    }
}