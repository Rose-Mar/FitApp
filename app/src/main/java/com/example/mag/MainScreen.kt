package com.example.mag

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.mag.compose.AddTrainingCars
import com.example.mag.compose.BasicNavigationButton
import com.example.mag.compose.Trainings
import com.example.mag.database.AppDatabase
import com.example.mag.navigation.Navigation
import com.example.mag.viewModel.TrainingViewModel

@Composable
fun MainScreen(navController: NavController, context: Context){



    val trainingDao = Room.databaseBuilder(context, AppDatabase::class.java, "database-name")
        .build()
        .userDao()




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


        //            viewModel.showTrainings()

                AddTrainingCars(trainingViewModel = viewModel)
                Trainings(trainings = viewModel.trainings)

                    val context = LocalContext.current



        BasicNavigationButton(navController = navController, value = "Start training", route ="StartTraining" ) {

        }


//                    Button(onClick = {
//                        context.startActivity(Intent(context, StartTraining::class.java))
//                    }) {
//                        Text(text = "Start training")
//                    }
    }


}