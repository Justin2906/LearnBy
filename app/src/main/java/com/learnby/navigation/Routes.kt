package com.learnby.navigation

sealed class Routes (val route: String){
    object Login : Routes("Login")
    
}