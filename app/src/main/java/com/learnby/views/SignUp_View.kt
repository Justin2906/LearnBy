package com.learnby.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.learnby.navigation.Routes
import com.learnby.ui.theme.btn
import com.learnby.ui.theme.fondo200
import com.learnby.viewModel.RegisterViewModel

@Composable
fun SignUpPage(navController: NavHostController, RegisterViewModel: RegisterViewModel) {

    val username = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }

    Box(
        Modifier
            .fillMaxWidth()
            .background(fondo200),
        contentAlignment = Alignment.BottomCenter
    ) {
        ConstraintLayout {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                ConstraintLayout {
                    val (surface, fab) = createRefs()
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(520.dp)
                            .constrainAs(surface) {
                                bottom.linkTo(parent.bottom)
                            },
                        color = Color(0xFFA1BBE2),
                        shape = RoundedCornerShape(
                            topStartPercent = 8,
                            topEndPercent = 8
                        )
                    ) {

                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "Welcome To LearnBy",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(360.dp),
                            style = TextStyle(
                                fontSize = 34.sp,
                            )
                        )

                        Spacer(modifier = Modifier.padding(16.dp))

                        TextField(
                            value = "",
                            onValueChange = {},
                            modifier = Modifier.fillMaxWidth(),
                            label = { Text(text = "Email") },
                            placeholder = { Text(text = "Ejemplo@gmail.com") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                backgroundColor = Color(0xFFeeeeee),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                        Spacer(modifier = Modifier.padding(14.dp))

                        TextField(
                            value = "",
                            onValueChange = {},
                            label = { Text(text = "Password") },
                            placeholder = { Text(text = "Ejemplo123") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                backgroundColor = Color(0xFFeeeeee),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )
                        Spacer(modifier = Modifier.padding(14.dp))

                        TextField(
                            value = "",
                            onValueChange = {},
                            label = { Text(text = "Full Name") },
                            placeholder = { Text(text = "Pepe grillo") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                backgroundColor = Color(0xFFeeeeee),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )

                        Spacer(modifier = Modifier.padding(14.dp))

                        TextField(
                            value = "",
                            onValueChange = {},
                            label = { Text(text = "Phone Number") },
                            placeholder = { Text(text = "678 567 789") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                            singleLine = true,
                            maxLines = 1,
                            colors = TextFieldDefaults.textFieldColors(
                                textColor = Color.Black,
                                backgroundColor = Color(0xFFeeeeee),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                            )
                        )
                        Spacer(modifier = Modifier.padding(8.dp))

                        Button(
                            onClick = { navController.navigate("LoginScreen") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFF994D51),
                                contentColor = Color.White,
                            )
                        ) {
                            Text(text = "Sign Up")
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                    }

                }
            }
            val (surface, fab) = createRefs()

            FloatingActionButton(
                modifier = Modifier
                    .size(72.dp)
                    .padding(0.dp, 22.dp)
                    .constrainAs(fab) {
                        top.linkTo(surface.top, margin = (-35).dp)
                        end.linkTo(surface.end, margin = 310.dp)
                    },
                backgroundColor = btn,

                onClick = { navController.navigate("loginScreen") }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Icon",
                    modifier = Modifier.size(42.dp),
                    tint = Color.White

                )
            }
        }

    }

}


