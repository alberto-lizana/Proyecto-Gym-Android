package com.kotlin.kotlingymanalytics.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable


private val GymAnalyticsColorScheme = darkColorScheme(
    primary = RojoOscuro,
    secondary = AzulClaro,
    tertiary = MoradoClaro,

    background = FondoOscuro,
    surface = FondoOscuro,

    onPrimary = BlancoCrema,
    onSecondary = BlancoCrema,
    onTertiary = BlancoCrema,
    onBackground = BlancoCrema,
    onSurface = BlancoCrema
)

@Composable
fun GymanalyticsTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GymAnalyticsColorScheme,
        typography = Typography,
        content = content
    )
}