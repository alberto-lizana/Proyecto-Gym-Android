package com.kotlin.kotlingymanalytics.navigation

sealed class GymAnalyticsScreen(val route: String) {

    data object Login : GymAnalyticsScreen("login")

    data object Home : GymAnalyticsScreen("home")

    data object Register : GymAnalyticsScreen("register")

    data object RecoverPassword : GymAnalyticsScreen("recover_password")

    data object CrearRutina : GymAnalyticsScreen("crear_rutina")

    data object AsignarEjercicios : GymAnalyticsScreen("asignar_ejercicios")

    data object  CalcularRm : GymAnalyticsScreen("calcular_rm")

}