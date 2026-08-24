package com.kotlin.kotlingymanalytics

import android.app.Application
import com.kotlin.kotlingymanalytics.core.utils.SoundManager

class GymAnalyticsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        SoundManager.init(this)
    }
}