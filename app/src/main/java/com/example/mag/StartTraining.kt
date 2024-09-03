package com.example.mag

import com.example.mag.viewModel.TrainingViewModel
import TrainingViewModelFactory
import android.content.Context
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mag.database.AppDatabase
import com.example.mag.path.requestLocationPermission
import com.example.mag.utils.CheckForLocationPermission
import com.example.mag.utils.CheckServicePermission

@Composable
fun StartTraining(navController: NavController,context: Context) {
    val localContext = LocalContext.current as ComponentActivity
    val database = AppDatabase.getInstance(localContext)
    val trainingDao = database.trainingDao()

    val trainingViewModel: TrainingViewModel = viewModel(
        factory = TrainingViewModelFactory(localContext.application, trainingDao)
    )

    val location by trainingViewModel.location.collectAsState()
    val time by trainingViewModel.time.collectAsState()
    val distance by trainingViewModel.distance.collectAsState()
    val calories by trainingViewModel.calories.collectAsState()
    val speed by trainingViewModel.speed.collectAsState()
    val requestingLocationUpdates by trainingViewModel.requestingLocationUpdates.collectAsState()

    LaunchedEffect(Unit) {
        trainingViewModel.startLocationUpdates()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.background2),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            CheckServicePermission()
            if (CheckForLocationPermission(localContext)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TrainingStatCard("Time", "$time s")
                        TrainingStatCard("Distance", "$distance m")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        TrainingStatCard("Calories", "$calories kcal")
                        TrainingStatCard("Speed", "$speed m/s")
                    }

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(
                            onClick = {
                                trainingViewModel.toggleLocationUpdates()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor =
                            MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(if (requestingLocationUpdates) "Pause" else "Resume")
                        }

                        Button(
                            onClick = {
                                // Kod do zakończenia treningu
                                navController.navigate("EndTraining")
                            },
                            colors = ButtonDefaults.buttonColors(containerColor =
                            MaterialTheme.colorScheme.primary)
                        ) {
                            Text("End Training")
                        }
                    }

                    location?.let {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Current Location: Lat=${it.latitude}, Lon=${it.longitude}",
                            fontSize = 20.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Please grant location permissions to start training.")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            requestLocationPermission(localContext)
                        }
                    ) {
                        Text("Grant Location Permission")
                    }
                }
            }
        }
    }
}

@Composable
fun TrainingStatCard(title: String, value: String) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(150.dp)
            .height(100.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = title, style = MaterialTheme.typography.labelLarge.copy(fontSize = 24.sp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = value, style = MaterialTheme.typography.bodyLarge.copy(fontSize = 30.sp))
        }
    }
}
