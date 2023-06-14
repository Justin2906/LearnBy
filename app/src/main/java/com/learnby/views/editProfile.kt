package com.learnby.views

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest


@Composable
fun EditProfileScreen(navController: NavController) {
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
            EditProfile(navController = navController)
        }
    }
}
@Composable
fun EditProfile(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    val newEmailState = rememberSaveable { mutableStateOf(user?.email ?: "") }
    val newUserNameState = rememberSaveable { mutableStateOf(user?.displayName ?: "") }
    val newPhotoState = rememberSaveable { mutableStateOf(user?.photoUrl ?: "") }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        newPhotoState.value = uri.toString()
    }
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
        }
        Text(
            text = "Editar perfil",
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.White

        )
        Text(
            text = "Email: ${user?.email}",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(bottom = 8.dp),
            color = Color.White
        )
        TextField(value = newUserNameState.value,
            onValueChange = {newUserNameState.value = it},

            label = {
                Text(
                    text = "${user?.displayName}",
                    color = Color.White
            )},

        )
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = { launcher.launch("image/*")}) {
                Text(text = "Cambiar foto de perfil",
                    color = Color.Black
                )
            }
            Button(onClick = { saveChanges(newEmailState.value, newUserNameState.value,
                newPhotoState.value as String, auth) }) {
                Text(
                    text = "Guardar cambios",
                    color = Color.Black
                )
            }
            
        }
    }
}

private fun saveChanges(newEmail: String, newUserName: String, newPhotoUrl: String, auth: FirebaseAuth) {
    val user = auth.currentUser
    val profileUpdates = UserProfileChangeRequest.Builder()
        .setPhotoUri(newPhotoUrl.toUri())
        .setDisplayName(newUserName)
        .build()

    user?.updateProfile(profileUpdates)
        ?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val updatedUser = auth.currentUser
                updatedUser?.updateEmail(newEmail)
                    ?.addOnCompleteListener { emailTask ->
                        if (emailTask.isSuccessful) {
                        } else {
                        }
                    }
            } else {
            }
        }
}