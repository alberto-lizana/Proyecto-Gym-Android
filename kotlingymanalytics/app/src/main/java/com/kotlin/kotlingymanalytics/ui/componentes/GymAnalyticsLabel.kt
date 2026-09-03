package com.kotlin.kotlingymanalytics.ui.componentes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema

@Composable
fun GymAnalyticsLabel (text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        color = BlancoCrema,
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}