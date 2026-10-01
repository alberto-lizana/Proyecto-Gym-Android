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
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsCrearCiclo
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsCrearRutina
import com.kotlin.kotlingymanalytics.ui.pantallas.GymAnalyticsMisCiclosRutinas
import com.kotlin.kotlingymanalytics.ui.viewmodel.CicloViewModel
import com.kotlin.kotlingymanalytics.ui.viewmodel.RutinaViewModel
import com.kotlin.kotlingymanalytics.ui.viewmodel.UsuarioViewModel

@Composable
fun GymAnalyticsNavigation() {

    val navController = rememberNavController()
    val usuarioViewModel: UsuarioViewModel = viewModel()
    val rutinaViewModel: RutinaViewModel = viewModel()
    val cicloViewModel: CicloViewModel = viewModel()

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

        // HOME/Gestion
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

                cicloViewModel = cicloViewModel,

                toCrearCiclo = {
                    navController.navigate(GymAnalyticsScreen.CrearCiclo.route)
                },

                toCreaRutina = {
                    navController.navigate(GymAnalyticsScreen.CrearRutina.route)

                },

                onStart = {

                },

                toMisCiclosRutinas = {
                    navController.navigate(GymAnalyticsScreen.MisCiclosRutinas.route)
                },

                toCalcularRm = {
                    navController.navigate(GymAnalyticsScreen.CalcularRm.route)
                }
            )
        }

        // RECOVER PASSWORD
        composable(GymAnalyticsScreen.RecoverPassword.route) {
            GymAnalyticsRecoverPassword(
                onRecuperar = { email, onSuccess, onError ->

                    usuarioViewModel.recuperarPassword(
                        email = email,
                        onSuccess = onSuccess,
                        onError = onError
                    )
                },

                onRestablecer = { email, codigo, nuevaPassword, onSuccess, onError ->

                    usuarioViewModel.restablecerPassword(
                        email = email,
                        codigo = codigo,
                        nuevaPassword = nuevaPassword,
                        onSuccess = onSuccess,
                        onError = onError
                    )
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
                    rutinaViewModel.limpiarBorrador()
                    navController.popBackStack(GymAnalyticsScreen.Home.route, false)
                }
            )
        }

        // Calcular RM
        composable(GymAnalyticsScreen.CalcularRm.route) {
            GymAnalyticsCalcularRm()
        }

        // Crar Ciclo
        composable(GymAnalyticsScreen.CrearCiclo.route) {
            GymAnalyticsCrearCiclo(
                viewModel = cicloViewModel,

                onCicloCreado = {
                    cicloViewModel.limpiarBorrador()
                    navController.popBackStack(GymAnalyticsScreen.Home.route, false)
                }
            )
        }

        composable(GymAnalyticsScreen.MisCiclosRutinas.route) {
            GymAnalyticsMisCiclosRutinas(
                cicloViewModel = cicloViewModel
            )
        }
    }
}