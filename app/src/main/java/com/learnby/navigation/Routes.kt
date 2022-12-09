package com.learnby.navigation

import com.example.learnby.R

sealed class Routes(
    val route: String,
    val icon : Int,
    val title : String,

    ) {
    object Login : Routes("login", R.drawable.cerrar_sesion,"Login")
    object SignUp : Routes("signUp", R.drawable.perfil,"SignUp")
    object Curses : Routes("curses", R.drawable.perfil,"Curses")
    object Ques : Routes("ques", R.drawable.perfil,"Quiz")
    object Py : Routes("py", R.drawable.perfil,"Python")
}