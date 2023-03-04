package com.learnby.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.learnby.navigation.Routes.*
import com.learnby.viewModel.CoursesViewModel
import com.learnby.viewModel.LoginViewModel
import com.learnby.viewModel.PythonDviewModel
import com.learnby.viewModel.RegisterViewModel
import com.learnby.views.*

@Composable
fun NavigationHost() {
    val navigationController = rememberNavController()
    NavHost(navController = navigationController, startDestination = SplashScreen.route)

    {
        composable(Login.route) { LoginPage(navigationController, LoginViewModel()) }
        composable(Register.route) { SignUpPage(navigationController, RegisterViewModel()) }
        composable(SplashScreen.route){SplashScreen(navigationController)}
        composable(Curses.route){ VistaCursos(navigationController, CoursesViewModel())}
        composable(Py.route){ PythonView(navigationController, PythonDviewModel()) }
    }
}