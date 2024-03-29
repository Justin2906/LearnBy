package com.learnby.views

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.learnby.model.Contador
import com.learnby.model.Courses.Python.Python
import com.learnby.navigation.Routes
import com.learnby.ui.theme.Shapes
import com.learnby.viewModel.PythonDviewModel
import com.learnby.viewModel.UserViewModel
import kotlin.math.absoluteValue

@Composable
fun PythonView(navController: NavController){
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = { BottomNavigationScreen( navController ) },
        scaffoldState = scaffoldState,
    ) {
        CursoPython(navController, PythonDviewModel(), UserViewModel())
    }
}

@Composable
fun CursoPython(navController: NavController, pythonDviewModel: PythonDviewModel, userViewModel: UserViewModel) {
    val data by pythonDviewModel.documentationList.observeAsState(mutableListOf())

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(0xFF212338))
            .verticalScroll(rememberScrollState())
    ) {
        Button(
            onClick = { navController.navigate(Routes.Ques.route) },
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(text = "Pon a prueba tus conocimientos")
        }

        pythonDviewModel.getDocumentation()
        Column {
            data.forEach { doc ->
                ExpandableCard(doc)
            }
        }


    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpandableCard(python: Python){
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        if (expandedState) 180f else 0f)
    
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .padding(8.dp)
            ,
        color = Color(0xFF373960),
        shape = Shapes.medium,
        onClick = {
            expandedState = !expandedState
        },

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = python.description,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                IconButton(
                    modifier = Modifier
                        .alpha(ContentAlpha.medium)
                        .weight(1f, true)
                        .rotate(rotationState),
                    onClick = { expandedState = !expandedState }
                ){
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }

            if (expandedState){
                val imageModifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))

                Spacer(modifier = Modifier.height(16.dp))

                Image(
                    painter = rememberImagePainter(python.imageResource),
                    contentDescription = null,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = python.info,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White
                )
            }
        }
    }
}




