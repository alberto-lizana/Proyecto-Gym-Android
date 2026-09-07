package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema

@Composable
fun GymAnalyticsDato(
    nombre: String,
    valor: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = nombre,
            color = BlancoCrema.copy(alpha = 0.70f),
            fontSize = 14.sp
        )

        Text(
            text = valor,
            color = BlancoCrema,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}