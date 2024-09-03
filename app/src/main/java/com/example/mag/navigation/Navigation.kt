package com.example.mag.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mag.MainScreen
import com.example.mag.RandomPathTry
import com.example.mag.StartTraining
import com.example.mag.database.PreferencesManager
import com.example.mag.startingScreens.ExtraQuestions
import com.example.mag.startingScreens.SignIn
import com.example.mag.startingScreens.WelcomeScreen
import com.example.mag.startingScreens.sign_in.SignInScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun Navigation(coroutineScope: CoroutineScope, context: Context) {
    val navController = rememberNavController()
    val sp = PreferencesManager(context)
    val startDestination: String
    val isLoggedIn = sp.getLogin(false)
    val isFilled = sp.getIsFill("isFill", false)

    startDestination = if (isLoggedIn) {
        if (isFilled) {
            "MainScreen"
        } else {
            "ExtraQuestions"
        }
    } else {
        "WelcomeActivity"
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = "WelcomeActivity") {
            WelcomeScreen(navController, context)
        }
        composable(route = "ExtraQuestions") {
            ExtraQuestions(navController = navController, context = context)
        }
        composable(route = "SignInGoogle") {
            SignInScreen(navController = navController, coroutineScope = coroutineScope)
        }
        composable(route = "MainScreen") {
            MainScreen(navController = navController, context = context)
        }
        composable(route = "SignIn") {
            SignIn(navController = navController, context = context)
        }
        composable(route = "StartTraining") {
            StartTraining(navController = navController, context = context)
        }
        composable(route = "RandomPathTry/{distance}") { backStackEntry ->
            val distance = backStackEntry.arguments?.getString("distance")?.toInt() ?: 5
            RandomPathTry(navController = navController, context = context, distance = distance)

        }
    }
}