package com.example.mag.startingScreens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mag.R
import com.example.mag.compose.BasicNavigationButton
import com.example.mag.compose.NormalTextInput
import com.example.mag.compose.TitleTextComponent
import com.example.mag.database.PreferencesManager

@Composable
fun ExtraQuestions(navController: NavController, context: Context) {
    var username by remember { mutableStateOf("") }
    var birthday by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

    val prefsManager = remember { PreferencesManager(context) }

    Log.d("SharedPreferences", "Username:" +
            " ${prefsManager.getData("username", "")}")
    Log.d("SharedPreferences", "IsLogged: ${prefsManager.getLogin(false)}")

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
        ) {
            TitleTextComponent(value = stringResource(id = R.string.extra_quests_text))

            Spacer(modifier = Modifier.height(16.dp))

            NormalTextInput(
                label = stringResource(id = R.string.enter_username),
                value = username,
                onValueChanged = { newValue -> username = newValue },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NormalTextInput(
                label = stringResource(id = R.string.enter_birthday),
                value = birthday,
                onValueChanged = { newValue -> birthday = newValue },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NormalTextInput(
                label = stringResource(id = R.string.enter_gender),
                value = gender,
                onValueChanged = { newValue -> gender = newValue },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NormalTextInput(
                label = stringResource(id = R.string.enter_height),
                value = height,
                onValueChanged = { newValue -> height = newValue },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(8.dp))

            NormalTextInput(
                label = stringResource(id = R.string.enter_weight),
                value = weight,
                onValueChanged = { newValue -> weight = newValue },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (username.isNotEmpty() && birthday.isNotEmpty() &&
                gender.isNotEmpty() && height.isNotEmpty() && weight.isNotEmpty()) {
                BasicNavigationButton(
                    navController = navController,
                    value = "Let's start!",
                    route = "com.example.mag.MainScreen",
                    onClickAction = {
                        prefsManager.saveData(
                            username,
                            birthday,
                            gender,
                            height.toInt(),
                            weight.toInt(),
                            true
                        )
                    }
                )
            }
        }
    }
}
