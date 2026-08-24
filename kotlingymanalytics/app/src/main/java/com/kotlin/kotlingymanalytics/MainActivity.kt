package com.kotlin.kotlingymanalytics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kotlin.kotlingymanalytics.navigation.GymAnalyticsNavigation
import com.kotlin.kotlingymanalytics.ui.theme.GymanalyticsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            GymanalyticsTheme {
                GymAnalyticsNavigation()
            }
        }
    }
}