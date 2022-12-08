package com.learnby.model

import androidx.annotation.DrawableRes

data class Questions(
    val id : Int,
    @DrawableRes val imageResource: Int,
    val Pregunta: String,
    val Respuestas: List<String>
)
