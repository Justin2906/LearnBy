package com.learnby.views

import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.input.pointer.consumeAllChanges
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.learnby.MainActivity
import com.learnby.navigation.Routes
import com.learnby.ui.theme.Purple700
import com.learnby.viewModel.LoginViewModel

@Composable
fun LoginPage(
    navController: NavHostController, LoginViewModel: LoginViewModel
) {
    val email: String by LoginViewModel.memail.observeAsState(initial = "")
    val password: String by LoginViewModel.password.observeAsState(initial = "")
    val activity = LocalContext.current as MainActivity
    val viewModel: LoginViewModel by activity.viewModels()
    val loggedUser by viewModel.loggedUser().observeAsState(null)
    val logged by viewModel.logged().observeAsState(false)
    val isLoading by viewModel.isLoading().observeAsState(false)
    val googleError by viewModel.googleError().observeAsState("")
    val hasGoogleError by viewModel.hasGoogleError().observeAsState(false)

    if (loggedUser != null && !logged) {
        loggedUser!!.displayName?.let {
            PopUpLogin(it) {
                viewModel.logIn()
                navController.navigate("curses")

            }
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .background(Color(0xFFE0E5F3))
    ) {

        AndroidLogo(
            backgroundColor = MaterialTheme.colors.background, contentColor = Color(0xFF5D61E6), padding = 40.dp
        )

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {

            ConstraintLayout {

                val (surface, fab) = createRefs()
                val offset = Offset(5.0f, 10.0f)

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(420.dp)
                        .constrainAs(surface) {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color(0xFFA1BBE2),
                    shape = RoundedCornerShape(
                        topStartPercent = 8,
                        topEndPercent = 8
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Text(
                            fontWeight = FontWeight.Bold,
                            text = "Welcome To LearnBy",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(360.dp),
                            style = TextStyle(
                                fontSize = 35.sp,
                            )
                        )
                        Spacer(modifier = Modifier.padding(10.dp))

                        TextField(
                            value = email,
                            onValueChange = {LoginViewModel.onLoginChanged(it,password)},
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
                        Spacer(modifier = Modifier.padding(10.dp))

                        TextField(
                            value = password,
                            onValueChange = { LoginViewModel.onLoginChanged(email,it)},
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
                        Spacer(modifier = Modifier.padding(8.dp))
                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(
                                    0xFF994D51
                                )
                            )
                        ) {
                            Text(text = "Sign In")
                        }

                        Spacer(modifier = Modifier.padding(4.dp))
                        Text(text = "Or",
                            textAlign = TextAlign.Center,
                            modifier = Modifier.width(360.dp),
                            fontWeight = FontWeight.Black
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        Button(
                            onClick = { viewModel.logInWithGoogle(activity) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color(0xFFE0DADA)
                            )
                        ) {
                            Text(text = "Sign In With ")
                            Text(text = "G", color = Color.Blue)
                            Text(text = "o", color = Color.Red)
                            Text(text = "o", color = Color.Yellow)
                            Text(text = "g", color = Color.Blue)
                            Text(text = "l", color = Color.Green)
                            Text(text = "e", color = Color.Red)

                        }
                        if (hasGoogleError){
                            Spacer(modifier = Modifier.size(20.dp))
                            Text(text = googleError, color = Color.Red)
                        }

                        if (isLoading){
                            CircularProgressIndicator()
                        }

                        ClickableText(
                            text = AnnotatedString("Do not have an Account?"),
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                            ),
                            onClick = {navController.navigate(Routes.Register.route)}
                        )

                    }

                }

            }

        }
    }
}

@Composable
fun AndroidLogo(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color,
    padding: Dp = 30.dp
) {

    val eyes = remember { mutableStateOf(0.0f) }

    androidx.compose.foundation.Canvas(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)
            .background(Color(0xFFE0E5F3))
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consumeAllChanges()
                        eyes.value += dragAmount.x * 0.12f
                    },
                    onDragEnd = {
                        eyes.value = 0f
                    }
                )
            }
    ) {
        drawArc(
            startAngle = -180f,
            sweepAngle = 180f,
            useCenter = true,
            color = contentColor,
            size = Size(size.minDimension, size.minDimension),
            topLeft = Offset(0f, size.minDimension * 0.5f)
        )

        drawCircle(
            color = backgroundColor,
            center = Offset(size.minDimension * 0.3f + eyes.value, size.minDimension * 0.8f),
            radius = size.minDimension * 0.04f
        )

        drawCircle(
            color = backgroundColor,
            center = Offset(size.minDimension * 0.7f + eyes.value, size.minDimension * 0.8f),
            radius = size.minDimension * 0.04f
        )

        rotate(
            degrees = 340f,
            pivot = Offset(size.minDimension * 0.2f, size.minDimension * 0.4f)
        ) {
            drawRoundRect(
                color = contentColor,
                size = Size(size.minDimension * 0.03f, size.minDimension * 0.22f),
                cornerRadius = CornerRadius(size.minDimension * 0.02f),
                topLeft = Offset(
                    size.minDimension * 0.2f + (eyes.value * 0.3f),
                    size.minDimension * 0.4f
                )
            )
        }

        rotate(
            degrees = 20f,
            pivot = Offset(size.minDimension * 0.8f, size.minDimension * 0.4f)
        ) {
            drawRoundRect(
                color = contentColor,
                size = Size(size.minDimension * 0.03f, size.minDimension * 0.22f),
                cornerRadius = CornerRadius(size.minDimension * 0.02f),
                topLeft = Offset(
                    size.minDimension * 0.8f + (eyes.value * 0.3f),
                    size.minDimension * 0.4f
                )
            )
        }

    }
}
@Composable
fun PopUpLogin(name: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text= {
            Text(
                text = name,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface
            )
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "Continuar")
            }
        },
        backgroundColor = MaterialTheme.colors.surface
    )
}
