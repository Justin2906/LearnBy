package com.learnby.views

import android.os.Bundle
import android.service.autofill.OnClickAction
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnby.R
import com.learnby.model.Questions
import com.learnby.navigation.Routes_menu
import com.learnby.ui.theme.LearnByTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import kotlin.math.absoluteValue

@Composable
fun VistaQuestion() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItems = listOf(
        Routes_menu.Pantalla_Perfil,
        Routes_menu.Pantalla_confi,
        Routes_menu.Cerrar_sesion
    )
    Scaffold(
        scaffoldState = scaffoldState,
        topBar ={TopBarQues(scope,scaffoldState)},
        drawerContent = {DrawerQues(
            scope,
            scaffoldState,
            navController,
            menu_items = navigationItems)},

        ){
        Question()
    }

}

@Composable
fun TopBarQues(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    var showMenu by remember{
        mutableStateOf(value = false)
    }
    TopAppBar (
        backgroundColor = Color(0xFF373960),
        title = { Text(text = "LearnBy", color = Color.White)},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu,
                    contentDescription =  "Icono de menu",
                    tint = Color.White)
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.Refresh,
                    contentDescription = "Boton refrescar",
                    tint = Color.White)

            }
            IconButton(onClick = { showMenu = !showMenu }) {
                Icon(imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Mas opciones",
                    tint = Color.White)

            }
            DropdownMenu(expanded = showMenu,
                onDismissRequest = { showMenu= false },
                modifier = Modifier.width(150.dp)
            ) {
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Person,
                        contentDescription = "Idiomas")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Idiomas")
                }
                DropdownMenuItem(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Share,
                        contentDescription = "Compartir")
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "Compartir")
                }
            }

        }
    )
}

@Composable
fun DrawerQues(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    navController: NavHostController,
    menu_items: List<Routes_menu>
){
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
        menu_items.forEach{item ->
            DrawerItemQues(item = item){
                navController.navigate(item.ruta){
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
fun DrawerItemQues(item: Routes_menu,
                   onItemClick: (Routes_menu) -> Unit
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(6.dp)
            .clip(RoundedCornerShape(percent = 12))
            .padding(8.dp)
            .clickable { onItemClick(item) },
    ){
        Image(painterResource(id = item.icon),
            modifier = Modifier.size(30.dp),
            contentDescription = item.title)
        Spacer(modifier = Modifier.width(12.dp))

        Text(text = item.title,
            style = MaterialTheme.typography.body1,
        )
    }
}

data class Preguntas(
    @DrawableRes val imageResource: Int,
    val Question: String,
    val Answer1: String,
    val Answer2: String,
    val Answer3: String,
    val AnswerCorrect: String
)

val preguntasList = listOf(
    Preguntas(R.drawable.pregunta1py,
        Question = "¿Cual es el error Aqui?",
        Answer1 ="Declaracion de variables",
        Answer2 = "Uso Condicionales",
        Answer3 = "Uso de bucles",
        "Uso de bucles"
    ),
    Preguntas(R.drawable.pregunta1py,
        Question = "Curso Basico Java",
        Answer1 ="Declaracion de variables",
        Answer2 = "Uso Condicionales",
        Answer3 = "Uso de bucles",
        "Uso de bucles"
    )
)


@Composable
fun Question(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF212338))
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

        Column {
            preguntasList(preguntasList = preguntasList)
        }
    }

}

@Composable
fun PreguntasCard(preguntas: Preguntas){
    val image = painterResource(preguntas.imageResource)

    var enabled by remember { mutableStateOf(true)}

    var counter = rememberSaveable { mutableStateOf(100) }

    var selected by remember { mutableStateOf(false) }

    var restar = if (selected) counter.value -=20 else counter.value= counter.value

    val color = if (enabled) Color.White else Color.Green

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
                text = preguntas.Question,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )
            Button(onClick = {enabled = !enabled},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                enabled = enabled
            ) {
                Text(
                    text = preguntas.Answer1,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {enabled = !enabled},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth(),
                enabled = enabled
            ) {
                Text(
                    text = preguntas.Answer2,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }

            Button(onClick = {enabled = !enabled},
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                enabled = enabled
            ) {
                Text(
                    text = preguntas.Answer3,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black
                )
            }


        }
    }
}

@Composable
fun preguntasList(preguntasList: List<Preguntas>){
    LazyColumn(modifier = Modifier
        .background(Color(0xFF212338))
    ){
        items(preguntasList){pregunta ->
            PreguntasCard(pregunta)
        }
    }
}

@Preview()
@Composable
fun TaskCompletedPreview() {
    LearnByTheme() {
        Surface {
            VistaQuestion()
        }
    }
}


