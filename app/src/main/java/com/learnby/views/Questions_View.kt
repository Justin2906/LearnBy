package com.learnby.views

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
import com.learnby.model.Contador
import com.learnby.model.Preguntas
import com.learnby.model.preguntasList
import com.learnby.navigation.Routes
import com.learnby.ui.theme.LearnByTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Question(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF212338))
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
            Button(
            onClick = { navController.navigate("ResultView") },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(8.dp)
            ) {
            Text(
                text = "Terminar Prueba",
                color = Color.Black
            )
            }

            preguntasList(preguntasList = preguntasList)
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
                onClick = {
                    if (preguntas.Answer1 == preguntas.AnswerCorrect){
                        Contador.puntos = (Contador.puntos) + 0.25f
                    }
                    enabled = !enabled},
                colors = ButtonDefaults.buttonColors(backgroundColor = color),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
                enabled = enabled

            ) {
                Text(
                    text = preguntas.Answer1,
                    style = MaterialTheme.typography.body2,
                    color = Color.Black,

                )
            }

            Button(
                onClick = {
                    if (preguntas.Answer2 == preguntas.AnswerCorrect){
                        Contador.puntos = (Contador.puntos) + 0.25f
                    }
                    enabled = !enabled
                          },
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
                onClick = {
                    if (preguntas.Answer3 == preguntas.AnswerCorrect){
                        Contador.puntos = (Contador.puntos) + 0.25f
                    }
                    enabled = !enabled
                          },
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