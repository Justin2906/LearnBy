package com.learnby.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.learnby.model.FavCourses
import com.learnby.viewModel.FavViewModel

@Composable
fun VistaFavs(navController: NavController, viewModel: FavViewModel){
    val scaffoldState = rememberScaffoldState()
    val data by viewModel.favCursesList.observeAsState(mutableListOf())

    viewModel.viewAll()

    Scaffold(
        bottomBar = { BottomNavigationScreen( navController ) },
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF212338))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            Column(
                modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Mis Cursos Guardados",
                    style = MaterialTheme.typography.h5,
                    color = Color.White,
                )
            }


            data.forEach { curso ->
                FavsCourses(curso,navController, viewModel)
            }

            Spacer(modifier = Modifier.height(100.dp))

        }
    }
}

@Composable
fun FavsCourses(
    favCourses: FavCourses,
    navController: NavController,
    viewModel: FavViewModel,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    background: Color = Color(0xFF373960),
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium
) {
    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = BorderStroke(1.dp,Color.White),
        modifier = modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(horizontalArrangement = Arrangement.Center) {
                Text(
                    text = favCourses.tittle + " " + favCourses.dificulty,
                    color = Color.White,
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.width(160.dp))

                Image(
                    painter = rememberImagePainter(favCourses.iconImageResource),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = Color.White, thickness = 1.dp)

            Button(onClick = { navController.navigate(favCourses.tittle + "View") }) {
                Text(text = "Ir al curso", color = Color.Black)
            }

            Button(onClick = {
                viewModel.deleteFav(favCourses.id)
                navController.navigate("Favs")
            }) {
                Text(text = "Eliminar de mis favoritos", color = Color.Black)
            }

        }
    }
}

