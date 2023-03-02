package com.learnby.views

import android.content.ContentValues
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.learnby.R
import com.google.firebase.firestore.FirebaseFirestore
import com.learnby.model.Cursos
import com.learnby.navigation.Routes
import kotlinx.coroutines.tasks.await

@Composable
fun VistaCursos(navController: NavController) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
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
        Cursos(navController = navController, viewAll())
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
                text = cursos.tittle + "-" + cursos.dificulty,
                style = typography.h4,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            Text(
                text = cursos.description,
                style = typography.body1,
                modifier = Modifier.padding(15.dp).align(Alignment.CenterHorizontally),
                color = Color.White
            )

            Button(
                onClick = { navController.navigate(Routes.Py.route)},
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
@Composable
fun Cursos(navController: NavController, lista: List<Cursos>){
    Column {
        CursesList(lista, navController = navController)
    }
}

@Composable
fun viewAll(): List<Cursos> {
    val db = FirebaseFirestore.getInstance()
    val nombre_coleccion = "Curses"
    var listaPlayers by remember { mutableStateOf(listOf<Cursos>()) }
    var datosJugadores by remember { mutableStateOf("") }

    db.collection(nombre_coleccion)
        .get()
        .addOnSuccessListener { search ->
            for (encontrado in search) {
                //datosJugadores += "${document.id}: ${document.data}\n\n"
                listaPlayers += Cursos(
                    R.drawable.pythoncurso,
                    encontrado["nameCurse"].toString(),
                    encontrado["dificulty"].toString(),
                    encontrado["description"].toString(),
                )
                //Log.d("Datos", listaPlayers.toString())
            }

            datosJugadores += listaPlayers.toString()
            if (datosJugadores.isEmpty()){
                datosJugadores = "No existen registros"
            }
        }
        .addOnFailureListener { exception ->
            datosJugadores = "No se a podido recoger los datos"
            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
        }

    return listaPlayers
}