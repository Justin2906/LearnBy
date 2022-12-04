package com.learnby.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.learnby.R
import androidx.compose.ui.unit.dp

data class Cursos(
    @DrawableRes val imageResource: Int,
    val tittle: String,
    val description: List<String>
)

val cursosList = listOf(Cursos(R.drawable.pythoncurso, tittle = "Curso Basico Python", listOf("Declaracion de variables", "Uso Condicionales", "Uso de bucles")),
    Cursos(R.drawable.java, tittle = "Curso Basico Java", listOf("Declaracion de variables", "Uso Condicionales", "Uso de bucles")),
    Cursos(R.drawable.java_script1, tittle = "Curso Basico JavaScript", listOf("Declaracion de variables", "Uso Condicionales", "Uso de bucles")),
)

@Composable
fun VistaCursos(){
    Column(
        modifier = Modifier
            .background(Color(0xFF272928))
    ) {
        Text(text = "Cursos Disponibles", style = typography.h5, color = Color.White, modifier = Modifier.align(Alignment.CenterHorizontally))

        CursesList(cursosList = cursosList)
    }
}

@Composable
fun CursesCard(cursos: Cursos){
    val image = painterResource(id = cursos.imageResource)
        Surface(
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp,
            modifier = Modifier.padding(8.dp)
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
                    style = typography.h6,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )

                for (description in cursos.description){
                    Text(
                        text = description,
                        style = typography.body2,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
}

@Composable
fun CursesList(cursosList: List<Cursos>){
    LazyColumn{
        items(cursosList){curso ->
            CursesCard(curso)
        }
    }
}


@Preview
@Composable
fun PreviewVistaCursos(){
    CursesList(cursosList = cursosList)
}