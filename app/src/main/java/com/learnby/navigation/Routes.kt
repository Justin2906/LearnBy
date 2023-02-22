package com.learnby.navigation

import com.example.learnby.R

sealed class Routes(
    val route: String

    ) {
    object Curses : Routes("curses")
    object Ques : Routes("ques")
    object Py : Routes("py")

    object Login : Routes("LoginScreen")
    object Register : Routes("RegisterScreen")
}