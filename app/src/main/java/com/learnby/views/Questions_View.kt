package com.learnby.views

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
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
import com.learnby.navigation.Routes_menu
import com.learnby.ui.theme.LearnByTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
        QuestionView()
    }

}

data class Preguntas(
    @DrawableRes val imageResource: Int,
    val Pregunta: String,
    val Respuestas: List<String>
)

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
@Composable
fun QuestionView() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(
                Color(0xFF212338)
            ),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Text(
                text = "Python Quiz",
                fontSize = 20.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 25.dp)
                    .background(Color(0xFF373960), shape = RoundedCornerShape(10.dp))
            ) {
                val image = painterResource(com.example.learnby.R.drawable.logopython)
                Image(
                    painter = image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(260.dp)
                        .padding(10.dp)
                        .clickable { },
                )

                Text(
                    text = "Aqui aparecera la pregunta",
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    fontSize = 36.sp,
                    modifier = Modifier.padding(bottom = 5.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF373960))
                ) {
                    Text(text = "Opcion A",fontSize = 20.sp,color = Color.White)
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF373960))
                ) {
                    Text(text = "Opcion A",fontSize = 20.sp,color = Color.White)
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF373960))
                ) {
                    Text(text = "Opcion A",fontSize = 20.sp,color = Color.White)
                }

                Button(
                    onClick = { },
                    shape = RoundedCornerShape(50.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .padding(bottom = 10.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF373960))
                ) {
                    Text(text = "Opcion D", fontSize = 20.sp, color =  Color.White, textAlign = TextAlign.Center)
                }
            }




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


