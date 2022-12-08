package com.learnby.navigation

sealed class Routes (val route: String){
    object  Login : Routes("login")
    object SignUp : Routes("signUp")
    object  Curses : Routes("curses")
    object Ques: Routes("Ques")
}