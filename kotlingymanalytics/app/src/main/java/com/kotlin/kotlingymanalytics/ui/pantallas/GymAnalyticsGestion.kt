package com.kotlin.kotlingymanalytics.ui.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.kotlin.kotlingymanalytics.ui.theme.FondoClaro

import com.kotlin.kotlingymanalytics.ui.componentes.HomeIcon
import com.kotlin.kotlingymanalytics.ui.componentes.PersonIcon
import com.kotlin.kotlingymanalytics.ui.componentes.StatsIcon

@Composable
fun GymAnalyticsGestion(
    onLogout: () -> Unit
) {
    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = FondoClaro
            ) {

                NavigationBarItem(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    icon = { HomeIcon(contentDescription = "Inicio") },
                    label = { Text("Inicio") }
                )

                NavigationBarItem(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    icon = { StatsIcon(contentDescription = "Estadísticas") },
                    label = { Text("Stats") }
                )

                NavigationBarItem(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    icon = { PersonIcon (contentDescription = "Perfil") },
                    label = { Text("Perfil") }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (selectedTab) {
                0 -> GymAnalyticsHome()
                1 -> GymAnalyticsStats()
                2 -> GymAnalyticsPerfil(
                    onLogout = onLogout
                )
            }
        }
    }
}