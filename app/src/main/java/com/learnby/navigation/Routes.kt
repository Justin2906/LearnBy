package com.learnby.navigation

import com.example.learnby.R

sealed class Routes(
    val route: String,
    ) {
    object SplashScreen: Routes("SplashScreen")
    object Curses : Routes("curses")
    object Ques : Routes("ques")
    object Py : Routes("PythonView")
    object Login : Routes("LoginScreen")
    object Register : Routes("RegisterScreen")
    object Resultado: Routes("ResultView")
    object Profile: Routes("Profile")

}