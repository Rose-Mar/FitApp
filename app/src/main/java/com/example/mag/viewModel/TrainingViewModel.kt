package com.example.mag.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mag.database.AppDatabase
import com.example.mag.database.Training
import com.example.mag.database.TrainingDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID


class TrainingViewModel(private val trainingDao: TrainingDao): ViewModel() {


    var trainings: List<Training> = emptyList()

    fun addTraining(distance: String, trainingTime: String, calories: String, avgTime: String){
        val newTraining = Training(
            tid = UUID.randomUUID().toString(),
            distance = distance,
            trainingTime = trainingTime,
            calories = calories,
            avgTime = avgTime
        )

        viewModelScope.launch(Dispatchers.IO) {
            trainingDao.insertAll(newTraining)
        }
    }

    fun showTrainings(){

        viewModelScope.launch(Dispatchers.IO) {
            trainings = trainingDao.getAll()
            Log.d("TrainingViewModel", "Trainings from database: $trainings")
        }

    }


    fun isDatabaseEmpty(): Boolean {
        val trainings = trainingDao.getAll() // Assuming getAll() function retrieves all entries
        return trainings.isEmpty()

    }



        }
