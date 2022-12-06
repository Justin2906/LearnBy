package com.learnby.views

import android.os.Bundle
import android.service.autofill.OnClickAction
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learnby.R
import com.learnby.ui.theme.LearnByTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnByTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                  VistaQuestion()
                }
            }
        }
    }
}
@Composable
fun VistaQuestion() {
        Column(
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
        //val image = painterResource(id = R.drawable._848152fcef1014c0b5e4967)
        //Image(painter = image, contentDescription = null)
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
        
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    Color(0xFF272928)),
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .fillMaxHeight(),
                    ) {
                        Text(
                            text = "Python Quiz",
                            fontSize = 30.sp,
                            color = Color.White,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Row(
                            modifier = Modifier
                                .padding(15.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Button(
                                onClick = { /*TODO*/ },
                                modifier= Modifier
                                    .size(50.dp),  //avoid the oval shape
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                                    0xFF4CAF50
                                )
                                )
                            ) {
                                Text(
                                    text = "5 ",
                                    fontSize = 25.sp,
                                    textAlign = TextAlign.Justify
                                )
                            }

                            Spacer(modifier = Modifier.width(5.dp))

                            Text(
                                text = "Aciertos",
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(20.dp))

                            Text(
                                text = "Fallos",
                                fontSize = 20.sp,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Button(
                                onClick = { /*TODO*/ },
                                modifier= Modifier
                                    .size(50.dp),  //avoid the oval shape
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color(
                                    0xFFF44336
                                )
                                )
                            ) {
                                Text(
                                    text = "3 ",
                                    fontSize = 25.sp
                                )

                            }
                        }

                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(bottom = 40.dp)
                                .background(Color.White, shape = RoundedCornerShape(10.dp))
                        ) {
                            val image = painterResource(com.example.learnby.R.drawable.logopython)
                            Image(
                                painter = image,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(260.dp)
                                    .padding(10.dp)
                                    .clickable {  },
                                )

                            Text(
                                text = "Aqui aparecera la pregunta",
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontSize = 36.sp,
                                modifier = Modifier.padding(bottom = 10.dp)
                            )
                        }

                        Column(
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Row() {
                                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(14.dp)) {
                                    Text(text = "Opcion A", fontSize = 20.sp)
                                }

                                Spacer(modifier = Modifier.width(40.dp))

                                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(14.dp)) {
                                    Text(text = "Opcion B", fontSize = 20.sp)
                                }
                            }
                            Row(
                                modifier = Modifier.padding(top = 54.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(14.dp)) {
                                    Text(text = "Opcion C", fontSize = 20.sp)
                                }

                                Spacer(modifier = Modifier.width(40.dp))

                                Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(14.dp)) {
                                    Text(text = "Opcion D", fontSize = 20.sp)
                                }
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


