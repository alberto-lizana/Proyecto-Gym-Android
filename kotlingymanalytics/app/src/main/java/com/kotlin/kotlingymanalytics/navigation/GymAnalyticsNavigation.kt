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
import com.kotlin.kotlingymanalytics.data.session.SessionManager
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsAsignarEjercicios
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsCalcularRm
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsCrearRutina
import com.kotlin.kotlingymanalytics.ui.viewmodel.RutinaViewModel
import com.kotlin.kotlingymanalytics.ui.viewmodel.UsuarioViewModel

@Composable
fun GymAnalyticsNavigation() {

    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val rutinaViewModel: RutinaViewModel = viewModel()

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

                toRecuperar = {
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
                    SessionManager.cerrarSesion()
                    navController.navigate(GymAnalyticsScreen.Login.route) {
                        popUpTo(GymAnalyticsScreen.Home.route) {
                            inclusive = true
                        }
                    }
                },

                toCreaRutina = {
                    navController.navigate(GymAnalyticsScreen.CrearRutina.route)

                },

                onStart = {

                },

                toCalcularRm = {
                    navController.navigate(GymAnalyticsScreen.CalcularRm.route)
                }

            )
        }

        // RECOVER PASSWORD
        composable(GymAnalyticsScreen.RecoverPassword.route) {
            GymAnalyticsRecoverPassword(
                onRecuperar = { email ->
                    usuarioViewModel.recuperarPassword(email)
                },

                onVolverLogin = {
                    navController.popBackStack()
                }
            )
        }

        // CREAR RUTINA
        composable(GymAnalyticsScreen.CrearRutina.route) {
            GymAnalyticsCrearRutina(
                viewModel = rutinaViewModel,

                onContinuar = {
                    navController.navigate(
                        GymAnalyticsScreen.AsignarEjercicios.route
                    )
                }
            )
        }

        // Asignar Ejercicios
        composable(GymAnalyticsScreen.AsignarEjercicios.route) {
            GymAnalyticsAsignarEjercicios(
                viewModel = rutinaViewModel,

                onAsignarEjercicios = {
                    println("Hola")
                }
            )
        }

        // Calcular RM
        composable(GymAnalyticsScreen.CalcularRm.route) {
            GymAnalyticsCalcularRm()
        }

    }
}