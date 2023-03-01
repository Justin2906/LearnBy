package com.learnby.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
    ) {
        Question()
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
        /*Image(
            painterResource(id = item.icon),
            modifier = Modifier.size(30.dp),
            contentDescription = item.title
        )*/
        Spacer(modifier = Modifier.width(12.dp))

        Text(
            text = "",
            style = MaterialTheme.typography.body1,
        )
    }
}

@Composable
fun Question() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF212338))
    ) {
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
fun PreguntasCard(preguntas: Preguntas) {
    val image = painterResource(preguntas.imageResource)

    var enabled by remember { mutableStateOf(true) }

    var counter = rememberSaveable { mutableStateOf(100) }

    var selected by remember { mutableStateOf(false) }

    var restar = if (selected) counter.value -= 20 else counter.value = counter.value

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
            Button(
                onClick = { enabled = !enabled },
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

            Button(
                onClick = { enabled = !enabled },
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

            Button(
                onClick = { enabled = !enabled },
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
fun preguntasList(preguntasList: List<Preguntas>) {
    LazyColumn(
        modifier = Modifier
            .background(Color(0xFF212338))
    ) {
        items(preguntasList) { pregunta ->
            PreguntasCard(pregunta)
        }
    }
}