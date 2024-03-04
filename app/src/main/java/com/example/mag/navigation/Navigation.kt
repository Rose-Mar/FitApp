package com.example.mag.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mag.startingScreens.ExtraQuestions
import com.example.mag.startingScreens.SignUpActivity
import com.example.mag.startingScreens.WelcomeScreen
import com.example.mag.startingScreens.sign_in.SignInScreen
import kotlinx.coroutines.CoroutineScope


@Composable
fun Navigation(coroutineScope: CoroutineScope, context: Context) {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "WelcomeActivity") {
        composable(route = "WelcomeActivity") {
            WelcomeScreen(navController)

        }

        composable(route = "SignUpActivity") {
            SignUpActivity(navController = navController)
        }

        composable(route = "ExtraQuestions") {
            ExtraQuestions(navController = navController)
        }

        composable(route = "SignInGoogle") {
            SignInScreen(navController = navController,coroutineScope = coroutineScope)

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