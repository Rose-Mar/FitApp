package com.example.mag.startingScreens

import android.os.Bundle
import android.text.Layout
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mag.R
import com.example.mag.startingScreens.ui.theme.MagTheme

@Composable
fun WelcomeScreen(navController: NavController) {

    MagTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column {


                Greeting2("Change your life with me")
                SignUpButton(navController)
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
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "$name!",
        modifier = modifier
    )
}


@Composable
fun SignUpButton(navController: NavController) {
    Button(onClick = {

        navController.navigate("SignUpActivity")
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


