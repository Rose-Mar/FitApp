package com.example.mag

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.mag.compose.AddTrainingCars
import com.example.mag.compose.Trainings
import com.example.mag.database.AppDatabase
import com.example.mag.navigation.Navigation
import com.example.mag.viewModel.TrainingViewModel
import kotlinx.coroutines.CoroutineScope

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.rememberCoroutineScope

import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {





//    private val coroutineScope: CoroutineScope by lazy { rememberCoroutineScope() }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val trainingDao = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
            .build()
            .userDao()




        setContent {


            val viewModel = viewModel<TrainingViewModel>(
                factory = object : ViewModelProvider.Factory{
                    override fun <T : ViewModel> create(modelClass: Class<T>): T {
                        return TrainingViewModel(
                            trainingDao = trainingDao
                        ) as T
                    }
                }
            )



            Column {


                val coroutineScope = rememberCoroutineScope()
                Navigation(coroutineScope = coroutineScope, context = applicationContext)
//            viewModel.showTrainings()



//                AddTrainingCars(trainingViewModel = viewModel)
//                Trainings(trainings = viewModel.trainings)
//
//                    val context = LocalContext.current



//                Button(onClick = {
//                    navCo
//                    context.startActivity(Intent(context,
//                        StartTraining::class.java)) }) {
//
//
//                }
//                    Button(onClick = {
//                        context.startActivity(Intent(context, StartTraining::class.java))
//                    }) {
//                        Text(text = "Start training")
//                    }
                }


            }



        }

    }


//@Composable
//fun GoToPageButton(navController: NavController, where: String, text: String)
//{
//
//    Button(onClick = {
//        navController.navigate("$where")
//    }) {
//        Text(text = text)
//
//    }
//
//}












