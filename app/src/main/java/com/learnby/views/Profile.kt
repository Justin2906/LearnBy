package com.learnby.views

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.learnby.R
import com.google.firebase.auth.FirebaseAuth
import com.learnby.ui.theme.LearnByTheme
import com.learnby.viewModel.CoursesViewModel

@Composable
fun ProfileScreen(navController: NavController) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        bottomBar = { BottomNavigationScreen(navController = navController) },
        scaffoldState = scaffoldState,
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF212338))
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) {
            Profile()
        }
    }
}
@Composable
fun Profile() {
    val user = FirebaseAuth.getInstance().currentUser

    val imageModifier = Modifier
        .height(150.dp)
        .fillMaxWidth()

    Image(
        painter = painterResource(id = R.drawable.ic_fondo_profile),
        contentDescription = null,
        modifier = imageModifier,
        contentScale = ContentScale.Crop,
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(horizontalArrangement = Arrangement.Center) {
            if (user != null) {
                if (user.photoUrl?.isAbsolute == true) {
                    Image(
                        painter = rememberImagePainter(user.photoUrl),
                        contentDescription = "Avatar",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                }
            }

            Spacer(modifier = Modifier.width(160.dp))

            Button(
                onClick = { },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Editar perfil", color = Color.Black)
            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(color = Color.White, thickness = 1.dp)

        Text(
            text = "Mi Perfil",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 36.dp),
            color = Color.White,
        )

        Text(
            text = "Bienvenido: ${user?.displayName}",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.White,
            fontSize = 30.sp
        )

        Text(
            text = "Email: ${user?.email}",
            style = MaterialTheme.typography.body1,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.White,
            fontSize = 20.sp
        )

    }
}

@Preview
@Composable
fun PreviewProfile() {
    LearnByTheme {
        Profile()
    }
}
