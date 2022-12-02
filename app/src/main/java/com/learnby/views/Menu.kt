package com.learnby.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.learnby.ui.theme.LearnByTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnByTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    VistaMenu()
                }
            }
        }
    }
}

@Composable
fun VistaMenu() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar ={TopBar(scope,scaffoldState)},
        drawerContent = {Drawer()}
    ){

    }
}
@Composable
fun TopBar(
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    TopAppBar (
        title = { Text(text = "WELCOME")},
        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(imageVector = Icons.Filled.Menu,
                    contentDescription =  "Icono de menu")
            }

        }
    )
}

@Composable
fun Drawer(){
    val menu_items = listOf(
        "Perfil",
        "Settings",
        "LogOut"
    )
    Column() {
        menu_items.forEach{item ->
            TextButton(onClick = { /*TODO*/ }) {
                Text(item,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun VistaMenuPreview() {
    LearnByTheme {
        VistaMenu()
    }
}