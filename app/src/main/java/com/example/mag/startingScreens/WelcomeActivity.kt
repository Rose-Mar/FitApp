package com.example.mag.startingScreens

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mag.R
import com.example.mag.compose.NormalTextInput
import com.example.mag.compose.TitleTextComponent
import com.example.mag.compose.signComponents.LoginButton
import com.example.mag.compose.signComponents.LoginViaMail
import com.example.mag.compose.signComponents.PasswordTextInput
import com.example.mag.compose.signComponents.SignUpButton
import com.example.mag.startingScreens.ui.theme.MagTheme
import com.google.firebase.auth.FirebaseAuth

private lateinit var auth: FirebaseAuth

@Composable
fun WelcomeScreen(navController: NavController, context: Context) {



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

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleTextComponent(
                    value = stringResource(id = R.string.start_screen_text)
                )

                NormalTextInput(
                    value = stringResource(id = R.string.enter_email),
                    onValueChanged = { newValue -> email = newValue },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                PasswordTextInput(
                    value = stringResource(id = R.string.enter_password),
                    onValueChanged = { newValue -> password = newValue }
                )

                SignUpButton(navController, email = email, password = password, context = context)

                LoginViaMail(navController = navController)

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.width(16.dp))
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .weight(1f),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                    Text(
                        text = "or",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Divider(
                        modifier = Modifier
                            .height(1.dp)
                            .weight(1f),
                        thickness = 1.dp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                }


                LoginButton(
                    appName = "Google",
                    icon = painterResource(R.drawable.googleicon),
                    onClick = {
                        navController.navigate("SignInGoogle")
                    }
                )
            }

        }
    }
}












