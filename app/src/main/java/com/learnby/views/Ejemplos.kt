package com.learnby.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

//Ejemplo de Progress Bar
@Composable
fun CircularProgressBar(
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    color: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.Green,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 1000,
    animDelay: Int = 0
) {
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

    LaunchedEffect(key1 = true) {
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
            text = (curPecentage.value * number).toInt().toString() + "%",
            color = androidx.compose.ui.graphics.Color.White,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold
        )
    }

}

@Composable
fun animated() {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column {
        Button(onClick = { expanded = !expanded }) {
            Text(text = if (expanded) "Shrink" else "expanded")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.background(Color.LightGray)) {
            Text(
                text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                fontSize = 16.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(16.dp),
                maxLines = if (expanded) Int.MAX_VALUE else 2
            )
        }
    }
}

@Composable
fun MyButtonExample() {
    var selected by remember { mutableStateOf(false) }
    val color = if (selected) Color.Blue else Color.Yellow

    Button(
        onClick = { selected = !selected },
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text("Button")
    }
}

@Preview
@Composable
fun Preview() {
    animated()
}