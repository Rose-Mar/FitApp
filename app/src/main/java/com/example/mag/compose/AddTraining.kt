package com.example.mag.compose

import com.example.mag.viewModel.TrainingViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mag.database.Training


@Composable
fun AddTraining(trainingViewModel: TrainingViewModel) {
    var distance by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = distance,
            onValueChange = { distance = it },
            label = { Text("Distance (km)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = duration,
            onValueChange = { duration = it },
            label = { Text("Duration (minutes)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                val training = Training(
                    timestamp = System.currentTimeMillis(),
                    distance = distance.toFloatOrNull() ?: 0f,
                    duration = (duration.toLongOrNull() ?: 0L) * 60 * 1000
                )
//                trainingViewModel.addTraining(training)
                distance = ""
                duration = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Training")
        }
    }
}