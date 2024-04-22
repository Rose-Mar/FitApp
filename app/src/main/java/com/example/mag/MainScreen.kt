package com.example.mag

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.room.Room
import com.example.mag.compose.AddTrainingCars
import com.example.mag.compose.BasicNavigationButton
import com.example.mag.compose.MapViewContent
import com.example.mag.compose.Trainings
import com.example.mag.database.AppDatabase
import com.example.mag.navigation.Navigation
import com.example.mag.viewModel.TrainingViewModel


private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
private const val MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION = 66

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
    val conte = LocalContext.current as ComponentActivity

    checkLocationPermission(conte)


    Column {


        //            viewModel.showTrainings()

                AddTrainingCars(trainingViewModel = viewModel)
                Trainings(trainings = viewModel.trainings)

                    val context = LocalContext.current






        BasicNavigationButton(navController = navController, value = "Start training", route ="StartTraining" ) {

        }

        BasicNavigationButton(navController = navController, value = "Start random location", route ="RandomPathTry" ) {

        }

        MapViewContent()

    }


}



private fun checkLocationPermission(context: ComponentActivity) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                AlertDialog.Builder(context)
                    .setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->
                        //Prompt the user once explanation has been shown
                        requestLocationPermission(context)
                    }
                    .create()
                    .show()
            } else {
                // No explanation needed, we can request the permission.
                requestLocationPermission(context)
            }
    }
}
private fun checkBackgroundLocation(activity: ComponentActivity) {
    if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        requestBackgroundLocationPermission(activity)
    }
}


private fun requestLocationPermission(activity: ComponentActivity) {
    ActivityCompat.requestPermissions(
        activity,
        arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
        ),
        MY_PERMISSIONS_REQUEST_LOCATION
    )
}

private fun requestBackgroundLocationPermission(activity: ComponentActivity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ),
            MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION
        )
    } else {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    }
}