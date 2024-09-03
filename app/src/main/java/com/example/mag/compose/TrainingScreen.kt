package com.example.mag.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mag.viewModel.TrainingSessionViewModel
import kotlinx.coroutines.launch

@Composable
fun TrainingScreen(navController: NavController) {
    val viewModel: TrainingSessionViewModel = viewModel()

    val distance by viewModel.distance.collectAsState()
    val time by viewModel.time.collectAsState()
    val calories by viewModel.calories.collectAsState()
    val speed by viewModel.speed.collectAsState()

    val coroutineScope = rememberCoroutineScope()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Training Session",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Distance: %.2f km".format(distance),
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Time: %02d:%02d".format(time / 60000, (time / 1000) % 60),
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Calories: $calories",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Speed: %.2f km/h".format(speed),
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = { coroutineScope.launch { viewModel.startTraining() } },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor
                    = Color.White),
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text(text = "Start")
                }

                Button(
                    onClick = { viewModel.stopTraining() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor
                    = Color.White),
                    modifier = Modifier.weight(1f).padding(8.dp)
                ) {
                    Text(text = "Stop")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.navigateUp() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Gray, contentColor
                = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Back")
            }
        }
    }
}
