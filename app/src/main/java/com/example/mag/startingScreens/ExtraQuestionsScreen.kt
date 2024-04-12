package com.example.mag.startingScreens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.mag.R
import com.example.mag.compose.BasicNavigationButton
import com.example.mag.compose.NormalTextInput
import com.example.mag.compose.TitleTextComponent
import com.example.mag.database.PreferencesManager

@Composable
fun ExtraQuestions(navController: NavController, context: Context){


    var username by remember {
        mutableStateOf("")
    }
    var birthday by remember {
        mutableStateOf("")
    }
    var gender by remember {
        mutableStateOf("")
    }
    var height by remember {
        mutableStateOf("")}
    var weight by remember {
        mutableStateOf("")
    }

    val prefsManager = remember {
        PreferencesManager(context)
    }



    Log.d("SharedPreferences", "Username: ${prefsManager.getData("username", "")}")
    Log.d("SharedPreferences", "IsLogged: ${prefsManager.getLogin(false)}")


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            TitleTextComponent(value = stringResource(id = R.string.extra_quests_text))
            
            NormalTextInput(
                value = stringResource(id = R.string.enter_username),
                onValueChanged = { newValue -> username = newValue},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

            NormalTextInput(
                value = stringResource(id = R.string.enter_birthday),
                onValueChanged = { newValue -> birthday = newValue},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

            NormalTextInput(
                value = stringResource(id = R.string.enter_gender),
                onValueChanged = { newValue -> gender = newValue},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text))

            NormalTextInput(
                value = stringResource(id = R.string.enter_height),
                onValueChanged = { newValue -> height = newValue},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

            NormalTextInput(
                value = stringResource(id = R.string.enter_weight),
                onValueChanged = { newValue -> weight = newValue},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))


            if(username.isNotEmpty() && birthday.isNotEmpty()&& gender.isNotEmpty()&& height.isNotEmpty() && weight.isNotEmpty()) {

                BasicNavigationButton(
                    navController,
                    value = "Let's start!",
                    route = "MainScreen",
                    onClickAction = {
                        prefsManager.saveData(
                            username,
                            birthday,
                            gender,
                            height.toInt(),
                            weight.toInt(),
                            true
                        )
                    })
            }
            //            else{
//                Toast.makeText(context, "Fill the information!", Toast.LENGTH_SHORT).show()
//            }

        }
        
    }
    
    
}