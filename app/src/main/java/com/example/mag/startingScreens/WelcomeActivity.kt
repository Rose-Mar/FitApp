package com.example.mag.startingScreens

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mag.R
import com.example.mag.compose.NormalTextComponent
import com.example.mag.compose.NormalTextInput
import com.example.mag.compose.PasswordTextInput
import com.example.mag.startingScreens.ui.theme.MagTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth



private lateinit var auth: FirebaseAuth

@Composable
fun WelcomeScreen(navController: NavController) {


     var email by remember {
         mutableStateOf("")
     }
     var password by remember {
         mutableStateOf("")}


    MagTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {


                NormalTextComponent(
                    value = stringResource(id = R.string.start_screen_text))


                NormalTextInput(
                    value = stringResource(id = R.string.enter_email),
                    onValueChanged = { newValue -> email = newValue},
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))

                PasswordTextInput(
                    value = stringResource(id = R.string.enter_password),
                    onValueChanged = {newValue -> password = newValue})

                SignUpButton(navController, email = email, password = password)
                Text("Email: $email")
                Text("Password: $password")

                LoginViaMail(navController = navController)
                LoginButton(appName = "Google",
                    icon = painterResource(R.drawable.googleicon),
                    onClick = {

                        navController.navigate("SignInGoogle")

                    })

            }

        }

    }
}



@Composable
fun SignUpButton(navController: NavController, email: String, password: String) {
    Button(onClick = {

auth = FirebaseAuth.getInstance()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser


                    navController.navigate("ExtraQuestions")
                } else {

                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
//                    Toast.makeText(
//                        applicationContext,
//                        "Authentication failed: ${task.exception?.message}",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
            }

    }) {


        Text(text = "Sign up")
        
    }
}

@Composable
fun LoginButton(
    appName: String,
    icon: Painter,
    onClick: () -> Unit
){
    Row (
    modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically    ) {
        Icon(
            painter = icon,
            contentDescription = "Login with $appName",
//            tint = Color.Gray,
            modifier = Modifier.size(48.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Log in with $appName",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }

}

@Composable
fun LoginViaMail(navController: NavController){
    Row {
        Text(text = "Already have an account?")
        Button(onClick = {
            navController.navigate("SignIn")
        }) {
            Text(text = "Login")

        }
    }
}


