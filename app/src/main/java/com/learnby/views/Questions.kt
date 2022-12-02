package com.learnby.views

import android.graphics.Paint.Style
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.learnby.ui.theme.LearnByTheme

@Composable
fun VistaQuestion() {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFFFFFF)),
            horizontalAlignment  = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Python Quiz",
            fontSize = 30.sp
        )
        Row (
            modifier = Modifier
                .padding(35.dp)
                ){
            Text(
                text = "Aciertos"
            )
            Text(
                text = "Fallos"
            )
        }
        val image = painterResource(id = R.drawable._848152fcef1014c0b5e4967)
        Image(painter = image, contentDescription = null)
        /*Button(onClick = { /*TODO*/ }) {
            Text("hola")
        }
        Text(
            text = "hola",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = "hola",
            fontSize = 16.sp
        )*/
    }
}

@Preview(showBackground = true)
@Composable
fun TaskCompletedPreview() {
    LearnByTheme() {
        Surface {
            VistaQuestion()
        }
    }
}


