package com.example.mag.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mag.viewModel.TrainingViewModel

@Composable
fun AddTrainingField(label: String, onValueChanged: (String)->Unit) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 12.dp)
            .border(width = 1.5.dp, color = Color.Black, RoundedCornerShape(12.dp)
            ),
        value = text,
        onValueChange = { text = it
            onValueChanged(it)},
        maxLines = 1,
        label ={ Text(text = label) }
    )
}


@Composable
fun AddTrainingCars(trainingViewModel: TrainingViewModel){

    var distance by remember { mutableStateOf("") }
    var avgTime by remember { mutableStateOf("") }
    var trainingTime by remember { mutableStateOf("") }
    var calories by remember { mutableStateOf("") }


    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ){
        item{
            AddTrainingField(label = "Distance"){ newValue -> distance = newValue}
            AddTrainingField(label = "Avg Time"){ newValue -> avgTime = newValue}
            AddTrainingField(label = "Training Time"){ newValue -> trainingTime = newValue}
            AddTrainingField(label = "Calories"){ newValue -> calories = newValue}



            Button(onClick = {


                trainingViewModel.addTraining(distance, trainingTime, calories, avgTime)
            }){
                Text(text = "Add training")
            }
        }
    }
}