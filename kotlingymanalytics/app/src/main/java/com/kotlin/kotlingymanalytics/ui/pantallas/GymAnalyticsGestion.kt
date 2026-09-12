package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.kotlin.kotlingymanalytics.ui.componentes.HomeIcon
import com.kotlin.kotlingymanalytics.ui.componentes.PersonIcon
import com.kotlin.kotlingymanalytics.ui.componentes.StatsIcon
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuro
import com.kotlin.kotlingymanalytics.ui.theme.AzulOscuroContraste
import com.kotlin.kotlingymanalytics.ui.theme.BlancoCrema
import com.kotlin.kotlingymanalytics.ui.theme.FondoOscuro
import com.kotlin.kotlingymanalytics.ui.theme.RojoOscuro

@Composable
fun GymAnalyticsGestion(
    onLogout: () -> Unit,
    toCreaRutina: () -> Unit,
    toCalcularRm: () -> Unit,
    onStart: () -> Unit
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            NavigationBar(
                containerColor = AzulOscuroContraste
            ) {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { HomeIcon(contentDescription = "Inicio") },
                    label = {
                        Text(
                            text = "Inicio",
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == 0) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = RojoOscuro,
                        selectedTextColor = RojoOscuro,
                        unselectedIconColor = BlancoCrema.copy(alpha = 0.45f),
                        unselectedTextColor = BlancoCrema.copy(alpha = 0.45f),
                        indicatorColor = AzulOscuro.copy(alpha = 0.35f)
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { StatsIcon(contentDescription = "Estadísticas") },
                    label = {
                        Text(
                            text = "Stats",
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == 1) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = RojoOscuro,
                        selectedTextColor = RojoOscuro,
                        unselectedIconColor = BlancoCrema.copy(alpha = 0.45f),
                        unselectedTextColor = BlancoCrema.copy(alpha = 0.45f),
                        indicatorColor = AzulOscuro.copy(alpha = 0.35f)
                    )
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { PersonIcon(contentDescription = "Perfil") },
                    label = {
                        Text(
                            text = "Perfil",
                            fontSize = 11.sp,
                            fontWeight = if (selectedTab == 2) FontWeight.SemiBold else FontWeight.Normal
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = RojoOscuro,
                        selectedTextColor = RojoOscuro,
                        unselectedIconColor = BlancoCrema.copy(alpha = 0.45f),
                        unselectedTextColor = BlancoCrema.copy(alpha = 0.45f),
                        indicatorColor = AzulOscuro.copy(alpha = 0.35f)
                    )
                )
            }
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(
                    Brush.linearGradient(
                        colors = listOf(FondoOscuro, AzulOscuro)
                    )
                )
        ) {
            when (selectedTab) {
                0 -> GymAnalyticsHome(toCrearRutina = toCreaRutina, onStart = onStart, toCalcularRm = toCalcularRm)
                1 -> GymAnalyticsStats()
                2 -> GymAnalyticsPerfil(onLogout = onLogout)
            }
        }
    }
}