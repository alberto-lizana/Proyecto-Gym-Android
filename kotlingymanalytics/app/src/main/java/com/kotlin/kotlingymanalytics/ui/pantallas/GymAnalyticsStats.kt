package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.componentes.GymAnalyticsTitulo

@Composable
fun GymAnalyticsStats() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {

        GymAnalyticsTitulo(
            titulo = "Stats"
        )

    }
}