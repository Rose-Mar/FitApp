package com.example.mag

import android.os.SystemClock
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun TrainingScreen(navController: NavController) {
    var elapsedTime by remember { mutableStateOf(0L) }
    var distance by remember { mutableStateOf(0.0) }
    var speed by remember { mutableStateOf(0.0) }
    var calories by remember { mutableStateOf(0.0) }
    var isRunning by remember { mutableStateOf(true) }
    val startTime = remember { SystemClock.elapsedRealtime() }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            elapsedTime = (SystemClock.elapsedRealtime() - startTime) / 1000
            distance = calculateDistance(elapsedTime)
            speed = calculateSpeed(distance, elapsedTime)
            calories = calculateCalories(distance)
            delay(1000L)
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF121212)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            TrainingInfoCard(label = "Czas", value = formatTime(elapsedTime))
            TrainingInfoCard(label = "Dystans", value = "${"%.2f".format(distance)} km")
            TrainingInfoCard(label = "Prędkość", value = "${"%.2f".format(speed)} km/h")
            TrainingInfoCard(label = "Kalorie", value = "${"%.2f".format(calories)} kcal")

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    isRunning = false
                    navController.popBackStack()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EA),
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Zakończ trening", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun TrainingInfoCard(label: String, value: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1E1E))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = label, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}

private fun calculateDistance(elapsedTime: Long): Double {
    // Example: 5 km per hour
    val speedKmPerHour = 5.0
    val hours = elapsedTime / 3600.0
    return speedKmPerHour * hours
}

private fun calculateSpeed(distance: Double, elapsedTime: Long): Double {
    val hours = elapsedTime / 3600.0
    return if (hours > 0) distance / hours else 0.0
}

private fun calculateCalories(distance: Double): Double {
    // Example: 60 kcal per km
    val caloriesPerKm = 60.0
    return distance * caloriesPerKm
}

private fun formatTime(seconds: Long): String {
    val hrs = seconds / 3600
    val mins = (seconds % 3600) / 60
    val secs = seconds % 60
    return "%02d:%02d:%02d".format(hrs, mins, secs)
}
