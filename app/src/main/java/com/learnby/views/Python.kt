package com.learnby.views

import androidx.annotation.DrawableRes
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.learnby.R
import com.learnby.navigation.Routes_menu
import com.learnby.ui.theme.LearnByTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


data class Python(
    @DrawableRes val imageResource: Int,
    val title: String,
    val description: List<String>
)

val pythonList = listOf(
    Python(
        R.drawable.uno,
        title = "Tipos de Datos Primitivos Simples",
        listOf("- Números (numbers): Secuencia de dígitos (pueden incluir el - para negativos y el . para decimales) que representan números.",
                "Ejemplo. 0, -1, 3.1415",
                "- Cadenas (strings): Secuencia de caracteres alfanuméricos que representan texto. Se escriben entre comillas simples o dobles.",
                "Ejemplo. ‘Hola’, “Adiós”",
                "- Booleanos (boolean): Contiene únicamente dos elementos True y False que representan los valores lógicos verdadero y falso respectivamente.")
    ),
    Python(
        R.drawable.uno,
        title = "Tipos de datos primitivos compuestos",
        listOf("- Listas (lists): Colecciones de objetos que representan secuencias ordenadas de objetos de distintos tipos. Se representan con corchetes y los elementos se separan por comas.",
                "- Tuplas (tuples). Colecciones de objetos que representan secuencias ordenadas de objetos de distintos tipos. A diferencia de las listas son inmutables, es decir, que no cambian durante la ejecución.",
                "- Diccionarios (dictionaries): Colecciones de objetos con una clave asociada. Se representan con llaves, los pares separados por comas y cada par contiene una clave y un objeto asociado separados por dos puntos.")
    ),
    Python(
        R.drawable.aritmeticos,
        title = "Operadores aritméticos",
        listOf("+ (suma), - (resta), * (producto), / (cociente), // (cociente división entera), % (resto división entera), ** (potencia).")
    ),
    Python(
        R.drawable.logicos,
        title = "Operadores lógicos",
        listOf("== (igual que), > (mayor que), < (menor que), >= (mayor o igual que), <= (menor o igual que), != (distinto de).")
    ),
    Python(
        R.drawable.subcadenas,
        title = "Subcadenas",
        listOf("c[i:j:k] : Devuelve la subcadena de c desde el carácter con el índice i hasta el carácter anterior al índice j, tomando caracteres cada k.")
    ),
    Python(
        R.drawable.funcadenas,
        title = "Funciones de cadenas",
        listOf("- len(c) : Devuelve el número de caracteres de la cadena c.",
                "- min(c) : Devuelve el carácter menor de la cadena c.",
                "- max(c) : Devuelve el carácter mayor de la cadena c.",
                "- c.upper() : Devuelve la cadena con los mismos caracteres que la cadena c pero en mayúsculas.",
                "- c.lower() : Devuelve la cadena con los mismos caracteres que la cadena c pero en minúsculas.",
                "- c.title() : Devuelve la cadena con los mismos caracteres que la cadena c con el primer carácter en mayúsculas y el resto en minúsculas.",
                "- c.split(delimitador) : Devuelve la lista formada por las subcadenas que resultan de partir la cadena c usando como delimitador la cadena delimitador. Si no se especifica el delimitador utiliza por defecto el espacio en blanco.")
    )
)

@Composable
fun VistaPythonCurso() {
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
        topBar ={TopBarPy(scope,scaffoldState)},
        drawerContent = {DrawerPy(
            scope,
            scaffoldState,
            navController,
            menu_items = navigationItems)},

        ){
        CursoPython()
    }

}

@Composable
fun TopBarPy(
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
fun DrawerPy(
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
            DrawerItemPy(item = item){
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
fun DrawerItemPy(item: Routes_menu,
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
fun Documentacion(python: Python){

    val image =  painterResource(python.imageResource)

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
                text = python.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                color = Color.White
            )

            for (description in python.description){
                Text(
                    text = description,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                    color = Color.White
                )
            }
        }
    }
}
@Composable
fun CursoPython(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF212338))
    ){
        Column(
            modifier = Modifier
                .height(210.dp)
                .padding(8.dp)
                .fillMaxWidth()
        ) {

            Box(modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(8.dp)){
                CircularProgressBar(percentage = 0.7f, number = 100)
            }

            Text(
                text = "Progreso del Curso",
                style = MaterialTheme.typography.h6,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )
            
            Button(
                onClick = { },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
            ) {
                Text(text = "Pon a prueba tus conocimientos")
            }
        }

        Column {
            PythonList(pythonList = pythonList)
        }
    }
}

@Composable
fun CircularProgressBarPy(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Green,
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
            color = Color.White,
            text = (curPecentage.value * number).toInt().toString() + "%",
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,

        )
    }

}

@Composable
fun PythonList(pythonList: List<Python>){
    LazyColumn(modifier = Modifier
        .background(Color(0xFF212338))
    ){
        items(pythonList){python ->
            Documentacion(python)
        }
    }
}

@Preview
@Composable
fun PreviewVistaCursos2(){
   VistaPythonCurso()
}

