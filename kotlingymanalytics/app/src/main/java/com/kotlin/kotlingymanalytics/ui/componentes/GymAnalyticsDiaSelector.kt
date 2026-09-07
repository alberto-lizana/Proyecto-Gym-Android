package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@Composable
fun GymAnalyticsDiaSelector(
    nombre: String,
    seleccionado: Boolean,
    onClick: () -> Unit
) {

    Surface(
        onClick = onClick,
        modifier = Modifier
            .width(42.dp)
            .height(42.dp),
        shape = RoundedCornerShape(10.dp),
        color = if (seleccionado) {
            RojoOscuro
        } else {
            AzulOscuro
        },
        tonalElevation = if (seleccionado) 6.dp else 0.dp
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = nombre,
                color = BlancoCrema,
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}
