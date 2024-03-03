package com.example.mag.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.mag.database.Training

@Composable
fun TrainingCard(training: Training){
    Column {
        Row{
            Text(text = training.trainingTime,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall)

            Text(text = training.distance,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall)

        }
        Row {
            Text(text = training.calories,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall)
            Text(text = training.trainingTime,
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleSmall)

        }

    }

}









@Composable
fun Trainings(trainings: List<Training>){


    LazyColumn {
        items(trainings){training ->
            TrainingCard(training)

        }

    }

}

