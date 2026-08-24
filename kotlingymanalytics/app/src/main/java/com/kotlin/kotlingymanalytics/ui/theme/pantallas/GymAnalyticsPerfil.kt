package com.kotlin.kotlingymanalytics.ui.theme.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.componentes.GymAnalyticsButton

@Composable
fun GymAnalyticsPerfil(
    onLogout: () -> Unit
){

    Column(
        modifier = Modifier.fillMaxSize()) {
        // datos del perfil, avatar, etc.
        GymAnalyticsButton(
            text = "CERRAR SESION",
            containerColor = AzulOscuro,
            onClick = onLogout
        )
    }

}
