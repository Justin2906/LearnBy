package com.learnby.views

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.learnby.model.Course
import com.learnby.viewModel.CoursesViewModel
import coil.compose.rememberImagePainter
import com.learnby.model.FavCourses

@Composable
fun VistaCursos(navController: NavController, viewModel: CoursesViewModel) {
    val scaffoldState = rememberScaffoldState()
    val data by viewModel.cursesList.observeAsState(mutableListOf())

    viewModel.viewAll()

    Scaffold(
        bottomBar = { BottomNavigationScreen(navController = navController) },
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF212338))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            data.forEach { curso ->
                StandardCard(viewModel,cursos = curso, navController = navController)
            }
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
fun StandardCard(
    viewModel: CoursesViewModel,
    cursos: Course,
    navController: NavController,
    modifier: Modifier = Modifier,
    elevation: Dp = 1.dp,
    background: Color = Color(0xFF373960),
    contentColor: Color = contentColorFor(background),
    shape: Shape = MaterialTheme.shapes.medium
) {
    var selected by rememberSaveable { mutableStateOf(true) }

    var icon = if (selected) Icons.Default.FavoriteBorder else Icons.Default.Favorite


    Card(
        backgroundColor = background,
        contentColor = contentColor,
        shape = shape,
        elevation = elevation,
        border = BorderStroke(1.dp,Color.White),
        modifier = modifier.padding(8.dp)
    ) {
        // Contenedor
        Column {
            val imageModifier = Modifier
                .height(230.dp)
                .fillMaxWidth()

            Row(
                Modifier
                    .fillMaxWidth()
                    .height(72.dp)
                    .padding(start = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Miniatura
                Box(
                    modifier = Modifier
                        .background(color = Color.LightGray, shape = CircleShape)
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = rememberImagePainter(cursos.iconImageResource),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(32.dp))

                Column(Modifier.fillMaxWidth()) {
                    // Encabezado
                    Text(text = cursos.tittle , style = MaterialTheme.typography.h6, color = Color.White)

                    // Subtítulo
                    CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                        Text(text = "Nivel " + cursos.dificulty, style = typography.body1)
                    }
                }
            }

            // Multimedia
            Image(
                painter = rememberImagePainter(cursos.imageResource),
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

            Row(Modifier.padding(start = 16.dp, end = 24.dp, top = 16.dp)) {

                // Texto de ayuda
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        text = cursos.description,
                        overflow = TextOverflow.Ellipsis,
                        style = typography.body2,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {

                Box(
                    Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                ) {
                    // Botones
                    Row(modifier = Modifier.align(Alignment.CenterStart)) {
                        TextButton(onClick = { navController.navigate(cursos.tittle + "View") }) {
                            Text(text = "Ver", color = Color.White, fontSize = 20.sp)
                        }
                    }

                    //Iconos
                    Row(modifier = Modifier.align(Alignment.CenterEnd)) {
                        IconButton(onClick = {
                            selected = !selected
                            Log.d("id", cursos.id)
                            viewModel.searchFav( cursos.id)
                        }) {
                            Icon(icon, contentDescription = null, tint = Color.Red)
                        }
                    }
                }
            }
        }
    }
}
