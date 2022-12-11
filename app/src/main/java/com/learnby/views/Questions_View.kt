package com.learnby.views

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
import com.example.learnby.R
import com.learnby.model.Preguntas
import com.learnby.model.preguntasList
import com.learnby.navigation.Routes
import com.learnby.ui.theme.LearnByTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun VistaQuestion(navController: NavController) {
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
        },

        ){
        QuestionsView()
    }

}


@Composable
fun DrawerQues(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavController,
    menu_items: List<Routes.Login>
) {
    Column {
        Image(
            painterResource(id = R.drawable.encabezado_menu),
            contentDescription = "Menu",
            modifier = Modifier
                .height(160.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(15.dp)
        )
        menu_items.forEach { item ->
            DrawerItemQues(item = item) {
                navController.navigate(item.route){
                    launchSingleTop = true
                }
                scope.launch {
                    scaffoldState.drawerState.close()
                }

            }
        }
    }
}

@Composable
fun DrawerItemQues(
    item: Routes,
    onItemClick: (Routes) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(percent = 12))
            .padding(8.dp)
            .clickable { onItemClick(item) },
    ) {
        Image(
            painterResource(id = item.icon),
            modifier = Modifier.size(30.dp),
            contentDescription = item.title
        )
        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = item.title,
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun QuestionsView(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF212338))
            .verticalScroll(rememberScrollState())
    ){
        Column(
            modifier = Modifier
                .height(150.dp)
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Python Quiz",
                style = MaterialTheme.typography.h3,
                color = Color.White,
            )
            Text(
                text = "Aqui podras poner a prueba tus conocimientos sobre lo aprendido en el Curso",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
        }
        PreguntasCard1()
        PreguntasCard2()
        PreguntasCard3()
        PreguntasCard4()
        PreguntasCard5()
    }
}

@Composable
fun PreguntasCard1(){
    val image = painterResource(R.drawable.logopython)

    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Green else Color.White
    val color2 = if (selected) Color.Red else Color.White

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
                .size(150.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally)

            Image(
                painter = image,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "¿Cuál de las siguientes opciones es una variable de tipo Float?",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )



            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "'8.2'",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "10.4",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "5",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PreguntasCard2() {
    val image = painterResource(R.drawable.mostrarporpantalla)

    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Green else Color.White
    val color2 = if (selected) Color.Red else Color.White

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
            Text(
                text = "¿Qué se muestra por pantalla?",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "20",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Tu edad es: 20",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "edad",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PreguntasCard3() {
    val image = painterResource(R.drawable.suma)

    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Green else Color.White
    val color2 = if (selected) Color.Red else Color.White

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
            Text(
                text = "¿Qué se muestra por pantalla?",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "7",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "12",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "\"Resultado\"",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PreguntasCard4() {
    val image = painterResource(R.drawable.mayorque)

    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Green else Color.White
    val color2 = if (selected) Color.Red else Color.White

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
            Text(
                text = "¿Qué se muestra por pantalla?",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "\"Es mayor\"",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Es mayor",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "No es mayor",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun PreguntasCard5() {
    val image = painterResource(R.drawable.logopython)

    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Green else Color.White
    val color2 = if (selected) Color.Red else Color.White

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
                .size(150.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .align(Alignment.CenterHorizontally)

            Image(
                painter = image,
                contentDescription = null,
                modifier = imageModifier,
                contentScale = ContentScale.Crop
            )
            Text(
                text = "¿Qué devuelve el metodo len()?",
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "La palabra separada por espacios",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color2),
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "nada",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {selected = !selected},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "devuelve la longitud de un objeto",
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }
        }
    }
}

@Preview()
@Composable
fun TaskCompletedPreview() {
    LearnByTheme() {
        Surface {
            VistaQuestion(navController = rememberNavController())
        }
    }
}


