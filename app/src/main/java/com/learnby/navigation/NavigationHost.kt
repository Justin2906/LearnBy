package com.learnby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.learnby.navigation.Routes_menu
import com.learnby.navigation.Perfilnav

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes_menu.Pantalla_Perfil.ruta
    ) {
        composable(Routes_menu.Pantalla_Perfil.ruta) {
            Perfilnav()
        }
    }
}