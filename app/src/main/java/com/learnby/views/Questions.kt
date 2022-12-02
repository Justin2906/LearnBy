package com.learnby.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            fontSize = 30.sp,
        )
        Row (
            modifier = Modifier
                .padding(35.dp)
                ){
            Button(onClick = { /*TODO*/ }, shape =RoundedCornerShape(100.dp)) {
                Text(
                    text = "5 ",
                    fontSize = 20.sp
                )
                Text(
                    text = "Aciertos",
                    fontSize = 20.sp
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(100.dp)){
                Text(
                    text = "3 ",
                    fontSize = 20.sp
                )
                Text(
                        text = "Fallos",
                fontSize = 20.sp
                )
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(Color.Gray, shape = RoundedCornerShape(100.dp))
                .padding(bottom = 40.dp)
        ) {
            val image = painterResource(com.example.learnby.R.drawable.logopython)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .size(260.dp)
                    .padding(10.dp),

            )

            Text(
                text = "Aqui aparecera la pregunta",
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 36.sp,
            )
        }
        
        Column(modifier = Modifier.padding(5.dp)) {
            Row() {
                Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(14.dp)) {
                    Text(text = "Opcion A", fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(14.dp)) {
                    Text(text = "Opcion B",fontSize = 20.sp)
                }
            }
            Row(
                modifier = Modifier.padding( top = 54.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(14.dp)) {
                    Text(text = "Opcion C",fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.width(40.dp))
                Button(onClick = { /*TODO*/ },shape = RoundedCornerShape(14.dp  )) {
                    Text(text = "Opcion D",fontSize = 20.sp)
                }
            }

        }
    }
}

@Preview()
@Composable
fun TaskCompletedPreview() {
    LearnByTheme() {
        Surface {
            VistaQuestion()
        }
    }
}


