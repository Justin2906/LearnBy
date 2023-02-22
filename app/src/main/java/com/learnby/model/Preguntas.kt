package com.learnby.model

import androidx.annotation.DrawableRes
import com.example.learnby.R

data class Preguntas(
    @DrawableRes val imageResource: Int,
    val Question: String,
    val Answer1: String,
    val Answer2: String,
    val Answer3: String,
    val AnswerCorrect: String
)


val preguntasList = listOf(
    Preguntas(
        R.drawable.pregunta1py,
        Question = "¿Cual es el error Aqui?",
        Answer1 = "Declaracion de variables",
        Answer2 = "Uso Condicionales",
        Answer3 = "Uso de bucles",
        "Uso de bucles"
    ),
    Preguntas(
        R.drawable.pregunta1py,
        Question = "Curso Basico Java",
        Answer1 = "Declaracion de variables",
        Answer2 = "Uso Condicionales",
        Answer3 = "Uso de bucles",
        "Uso de bucles"
    )
)