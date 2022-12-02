package com.learnby.views

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learnby.navigation.Routes

@Composable
fun ScreenQuestion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Questions.route){

        composable(Routes.Questions.route){
            VistaQuestion()
        }

    }
}
