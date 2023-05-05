package com.learnby.model.Courses.Java

import androidx.annotation.DrawableRes

data class Java (
    @DrawableRes val image: Int,
    val description: String,
    val info: String
)