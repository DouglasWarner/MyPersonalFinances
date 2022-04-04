package com.douglas.mypersonalfinances.ui

sealed class Screens(val route: String) {
    object Welcome : Screens(route = "Bienvenido")
    object Home : Screens(route = "Inicio")
    object Overview : Screens(route = "Resumen")
}