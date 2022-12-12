package com.learnby.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.learnby.model.Cursos
import com.learnby.model.cursosList
import com.learnby.navigation.Routes
import com.learnby.ui.theme.LearnByTheme

@Composable
fun VistaCursos(navController: NavController) {
    TopBarView(navController = navController, view_page = Cursos(navController = navController))

}

@Composable
fun Cursos(navController: NavController){
    Column {
        CursesList(cursosList = cursosList,
            navController = navController)
    }
}

@Composable
fun CursesCard(cursos: Cursos, navController: NavController
){
    val image = painterResource(cursos.imageResource)
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
                painter = image,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = cursos.tittle,
                style = typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            for (description in cursos.description){
                Text(
                    text = description,
                    style = typography.body2,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            }

            Button(
                onClick = { navController.navigate(Routes.Py.route)},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
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

@Composable
fun CursesList(cursosList: List<Cursos>, navController: NavController){
    LazyColumn(modifier = Modifier
        .background(Color(0xFF212338))
    ){
        items(cursosList){curso ->
            CursesCard(curso, navController = navController)
        }
    }
}
@Preview()
@Composable
fun Preview_curses() {
    LearnByTheme() {
        Surface {
            VistaCursos(navController = rememberNavController())
        }
    }
}
