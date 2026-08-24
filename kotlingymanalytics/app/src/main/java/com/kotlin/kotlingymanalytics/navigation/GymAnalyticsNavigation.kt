package com.kotlin.kotlingymanalytics.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotlin.kotlingymanalytics.ui.theme.pantallas.GymAnalyticsGestion
import com.kotlin.kotlingymanalytics.ui.theme.pantallas.GymAnalyticsLogin


const val LOGIN = "login"
const val HOME = "home"


@Composable
fun GymAnalyticsNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LOGIN
    ) {

        composable(LOGIN) {
            GymAnalyticsLogin(
                onLogin = {
                    navController.navigate(HOME) {
                        popUpTo(LOGIN) { inclusive = true }
                    }
                }
            )
        }

        composable(HOME) {
            GymAnalyticsGestion(
                onLogout = {
                    navController.navigate(LOGIN) {
                        popUpTo(HOME) { inclusive = true }
                    }
                }
            )
        }
    }
}