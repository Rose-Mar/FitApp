package com.example.mag.navigation

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import com.google.android.gms.location.LocationRequest
import kotlinx.coroutines.CoroutineScope


@Composable
fun Navigation(coroutineScope: CoroutineScope, context: Context) {
    val navController = rememberNavController()
    val sp = PreferencesManager(context)

    val startDestination: String
    Log.d("SharedPreferences", "Username: ${sp.getData("username", "")}")
    Log.d("SharedPreferences", "IsLogged: ${sp.getLogin(false)}")
    Log.d("SharedPreferences", "isFilled: ${sp.getIsFill("isFill", false)}")

    Log.d("SharedPreferences", "isFilled: ${sp.getData("username", "")}")



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
            SignInScreen(navController = navController,coroutineScope = coroutineScope)

        }
        
        composable(route = "MainScreen") {
            MainScreen(navController = navController, context =context)
        }
        composable(route= "SignIn"){
            SignIn(navController = navController, context = context)
        }
        composable(route= "StartTraining"){
            StartTraining(navController = navController, context = context)
        }
        composable(route= "RandomPathTry"){
            RandomPathTry(navController = navController, context = context)
        }
    }
}
//        composable(
//            route  = Screen.DetailScreen.route+"/{name}",
//            arguments = listOf(
//                navArgument("name"){
//                    type = NavType.StringType
//                    defaultValue = "Philip"
//                    nullable = true
//
//            }
//            )
//        ){ entry ->
////            DetailScreen(name = entry.arguments?.getString("name"))
//        }
//    }
//}


//@Composable
//fun MainScreen(navController: NavController){
//
//    var text by remember {
//        mutableStateOf("")
//    }
//
//
//    Column (
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 50.dp)
//        )
//    {
//
//        TextField(
//            value = text,
//            onValueChange = {
//                text = it
//
//        },
//            modifier = Modifier.fillMaxWidth())
//
//    Spacer(modifier = Modifier.height(8.dp))
//    Button(onClick = {
//        navController.navigate(Screen.DetailScreen.withArgs(text))
//
//
//    },
//        modifier = Modifier.align(Alignment.End)
//    ) {
//        Text(text = "To DetailScreen")
//    }
//
//    }
//}
//
//
//@Composable
//fun DetailScreen(name: String?){
//    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()){
//        Text(text = "Hello $name!")
//    }
//}