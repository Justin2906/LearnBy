package com.learnby.navigation

import com.example.learnby.R

sealed class Routes_menu(
    val icon : Int,
    val title : String,
    val ruta : String
){
    object Pantalla_Perfil: Routes_menu(R.drawable.perfil, title = "Perfil", ruta = "Pantalla_Perfil")
    object Pantalla_confi: Routes_menu(R.drawable.configuracion, title = "Configuracion", ruta = "Pantalla_confi")
    object Cerrar_sesion: Routes_menu(R.drawable.cerrar_sesion, title = "Cerrar sesion", ruta = "Login_View")
}
