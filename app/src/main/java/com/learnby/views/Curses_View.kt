package com.learnby.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.learnby.R
import com.learnby.model.Cursos
import com.learnby.model.cursosList
import com.learnby.navigation.Routes
import com.learnby.navigation.Routes_menu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TopBar(
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
fun Drawer(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState,
    //navController: NavigationNavHostController,
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
            DrawerItem(item = item,

                ){
                /*navController.navigate(item.ruta){
                    launchSingleTop = true
                }*/
                scope.launch {
                    scaffoldState.drawerState.close()
                }

            }
        }
    }
}

@Composable
fun DrawerItem(item: Routes_menu,
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
fun VistaCursos(navController: NavController) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val navigationItems = listOf(
        Routes_menu.Pantalla_Perfil,
        Routes_menu.Pantalla_confi,
        Routes_menu.Cerrar_sesion
    )

    Scaffold(
        scaffoldState = scaffoldState,
        topBar ={TopBar(scope,scaffoldState)},
        drawerContent = {Drawer(
            scope,
            scaffoldState,
            //navController,
            menu_items = navigationItems)},

        ){
        Cursos(navController = navController)
    }

}

@Composable
fun CircularProgressBar1(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
){
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPecentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )

    )

    LaunchedEffect(key1 = true){
        animationPlayed = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(radius * 2f),
    ) {
        Canvas(modifier = Modifier.size(radius * 2f)) {
            drawArc(
                color = color,
                -90f,
                360 * curPecentage.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
            )
        }

        Text(
            text = (curPecentage.value * number).toInt().toString() + "%",
            color = Color.Black,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }
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
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF525058)),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(top = 5.dp)
            ) {
                Text(
                    text = "Iniciar Curso",
                    color = Color.White

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
