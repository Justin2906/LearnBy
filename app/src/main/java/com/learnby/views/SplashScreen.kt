package com.learnby.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.learnby.R
import com.learnby.navigation.Routes
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(navController: NavHostController){
    LaunchedEffect(key1 = true){
        delay(4000)
        navController.popBackStack()
        navController.navigate(Routes.Login.route)
    }

    Splash()
}

@Composable
fun Splash(){
    val logo = painterResource(id = R.drawable.learnbylogo_sin_fondo)

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = logo,
            contentDescription = "Logo Peticiones",
            modifier = Modifier.size(550.dp)
        )
        Text(
            text = "Bienvenidos",
            color = Color.White,
            fontSize = 36.sp
        )

        UndeterminedCircularProgress()
    }
}

@Composable
fun UndeterminedCircularProgress() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        CircularProgressIndicator(color = Color.Red)

        Spacer(Modifier.height(16.dp))

        Text(text = "Cargando")
    }
}
