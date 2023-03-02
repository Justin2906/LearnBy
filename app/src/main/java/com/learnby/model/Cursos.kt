package com.learnby.model

import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.DrawableRes
import com.example.learnby.R

data class Cursos(
    @DrawableRes val imageResource: Int,
    val tittle: String,
    val dificulty: String?,
    val description: String,
    )



