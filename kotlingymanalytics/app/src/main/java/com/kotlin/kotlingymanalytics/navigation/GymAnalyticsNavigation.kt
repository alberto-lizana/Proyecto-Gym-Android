package com.kotlin.kotlingymanalytics.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsGestion
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsLogin
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsRecoverPassword
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsRegister

import androidx.lifecycle.viewmodel.compose.viewModel
import com.kotlin.kotlingymanalytics.ui.viewmodel.usuarioViewModel

@Composable
fun GymAnalyticsNavigation() {

    val navController = rememberNavController()
    val usuarioViewModel: usuarioViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = GymAnalyticsScreen.Login.route
    ) {

        // LOGIN ACCION
        composable(GymAnalyticsScreen.Login.route) {
            GymAnalyticsLogin(
                viewModel = usuarioViewModel,

                onLogin = {
                    navController.navigate(GymAnalyticsScreen.Home.route) {
                        popUpTo(GymAnalyticsScreen.Login.route) {
                            inclusive = true
                        }
                    }
                },

                onCreate = {
                    navController.navigate(GymAnalyticsScreen.Register.route)
                },

                onRecoverPassword = {
                    navController.navigate(GymAnalyticsScreen.RecoverPassword.route)
                }
            )
        }

        // REGISTER
        composable(GymAnalyticsScreen.Register.route) {
            GymAnalyticsRegister(
                viewModel = usuarioViewModel,

                toLogin = {
                    navController.popBackStack()
                }
            )
        }

        // HOME
        composable(GymAnalyticsScreen.Home.route) {
            GymAnalyticsGestion(
                onLogout = {
                    navController.navigate(GymAnalyticsScreen.Login.route) {
                        popUpTo(GymAnalyticsScreen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // RECOVER PASSWORD
        composable(GymAnalyticsScreen.RecoverPassword.route) {
            GymAnalyticsRecoverPassword(
                onRecovery = {
                    println("hola recuperacion")
                }
            )
        }

    }
}