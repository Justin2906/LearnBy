package com.learnby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learnby.navigation.Routes.*
import com.learnby.views.CursesList
import com.learnby.views.LoginPage
import com.learnby.views.SignUpPage
import com.learnby.model.cursosList
import com.learnby.views.VistaCursos


@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login.route){
        composable(Login.route){
            LoginPage(
                navSignUp = {
                    navController.navigate(SignUp.route)
                },
                navCurses = {
                    navController.navigate(Curses.route)
                }
            )
        }
        composable(SignUp.route){
            SignUpPage()

        }
        composable(Curses.route) {
            VistaCursos()
        }
    }


}