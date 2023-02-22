package com.learnby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learnby.navigation.Routes.*
import com.learnby.viewModel.LoginViewModel
import com.learnby.viewModel.RegisterViewModel
import com.learnby.views.*


@Composable
fun NavigationHost() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = Login.route)

    {
        composable(Login.route) { LoginPage(navigationController, LoginViewModel()) }
        composable(Register.route) { SignUpPage(navigationController, RegisterViewModel()) }

    }
}