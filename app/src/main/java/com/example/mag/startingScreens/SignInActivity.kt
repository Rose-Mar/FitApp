package com.example.mag.startingScreens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mag.R
import com.example.mag.compose.NormalTextInput
import com.example.mag.compose.TitleTextComponent
import com.example.mag.compose.signComponents.LoginButton
import com.example.mag.compose.signComponents.PasswordTextInput
import com.example.mag.compose.signComponents.SignInButton
import kotlinx.coroutines.CoroutineScope

@Composable
fun SignIn(navController: NavController, context: Context) {


    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    Box(
        modifier = Modifier.fillMaxSize(),

    ) {
        Image(
            painter = painterResource(id = R.drawable.background2),

            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            TitleTextComponent(
                value = stringResource(id = R.string.login_screen_text)
            )

            NormalTextInput(
                label = stringResource(id = R.string.enter_email),
                value = stringResource(id = R.string.enter_email),
                onValueChanged = { newValue -> email = newValue },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )


            PasswordTextInput(
                value = stringResource(id = R.string.enter_password),
                onValueChanged = { newValue -> password = newValue }
            )

            SignInButton(navController = navController,
                email = email, password = password, context = context)


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