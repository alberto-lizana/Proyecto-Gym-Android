package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import androidx.compose.ui.text.TextStyle

@Composable
fun GymAnalyticsTitulo(
    titulo: String,
    style: TextStyle = MaterialTheme.typography.headlineMedium,
    color: Color = BlancoCrema,
    fontWeight: FontWeight = FontWeight.Bold
) {
    Text(
        text = titulo,
        style = style,
        color = color,
        fontWeight = fontWeight
    )

    Spacer(
        modifier = Modifier.height(12.dp)
    )
}