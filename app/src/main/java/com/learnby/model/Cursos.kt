package com.learnby.model

import androidx.annotation.DrawableRes
import com.example.learnby.R

data class Cursos(
    val id: Int,
    @DrawableRes val imageResource: Int,
    val tittle: String,
    val description: List<String>,

    )

val cursosList = listOf(
    Cursos(
        id = 1,
        R.drawable.pythoncurso,
        tittle = "Curso Basico Python",
        listOf(
            "Declaracion de variables",
            "Uso Condicionales",
            "Uso de bucles"
        )
    ),
    Cursos
        (
        id = 2,
        R.drawable.java,
        tittle = "Curso Basico Java",
        listOf(
            "Declaracion de variables",
            "Uso Condicionales",
            "Uso de bucles"
        )
    ),
    Cursos(
        id = 3,
        R.drawable.java_script1,
        tittle = "Curso Basico JavaScript",
        listOf(
            "Declaracion de variables",
            "Uso Condicionales",
            "Uso de bucles"
        )
    )
)