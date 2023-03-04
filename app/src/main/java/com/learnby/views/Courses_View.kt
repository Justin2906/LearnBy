package com.learnby.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.learnby.model.Course
import com.learnby.navigation.Routes
import com.learnby.viewModel.CoursesViewModel
import coil.compose.rememberImagePainter


@Composable
fun VistaCursos(navController: NavController, viewModel: CoursesViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val data by viewModel.cursesList.observeAsState(mutableListOf())
    viewModel.viewAll()

    val navigationItems = listOf(
        Routes.Login
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBar(scope, scaffoldState) },
        drawerContent = {
            Drawer(
                scope,
                scaffoldState,
                navController,
                menu_items = navigationItems
            )
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF212338))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            data.forEach { curso ->
                CursesCard(cursos = curso, navController = navController)
            }
        }
    }
}

@Composable
fun CursesCard(cursos: Course, navController: NavController) {
    Surface(
        modifier = Modifier
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        color = Color(0xFF373960)
    ) {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            val imageModifier = Modifier
                .height(150.dp)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(8.dp))

            Image(
                painter = rememberImagePainter(cursos.imageResource),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = cursos.tittle + " - " + cursos.dificulty,
                style = typography.h4,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            Text(
                text = cursos.description,
                style = typography.body1,
                modifier = Modifier
                    .padding(15.dp)
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            Button(
                onClick = { navController.navigate(cursos.tittle + "View") },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = "Iniciar Curso",
                    color = Color.Black
                )
            }

        }


    }
}
