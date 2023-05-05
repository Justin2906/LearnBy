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
        R.drawable.python_logo_4k_i6,
        Question = "¿Una característica del ciclo while es que no conocemos con anticipación el número de iteraciones?",
        Answer1 = "verdadero",
        Answer2 = "falso",
        Answer3 = "ninguna de las anteriores",
        "verdadero"
    ),
    Preguntas(
        R.drawable.python_logo_4k_i6,
        Question = "¿La diferencia entre un ciclo for y un ciclo while es que el while se ejecuta en base a una condición, mientras que el for lo hace en base a una secuencia?",
        Answer1 = "verdadero",
        Answer2 = "Falso",
        Answer3 = "ningun",
        "verdadero"
    ),
    Preguntas(
        R.drawable.python_logo_4k_i6,
        Question = "En el código: a = (1, 5, 8, 4, 5) sum_x = 10 for i in a: sum_x += i print sum_x ¿Cuanto es el valor final de la variable sum_x?",
        Answer1 = "23",
        Answer2 = "33",
        Answer3 = "42",
        "33"
    ),
    Preguntas(
        R.drawable.python_logo_4k_i6,
        Question = "¿Es posible tener un ciclo infinito sobre un while en python?",
        Answer1 = "verdadero",
        Answer2 = "Falso",
        Answer3 = "Ambas",
        "verdadero"
    ),



)