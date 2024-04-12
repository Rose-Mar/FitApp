package com.example.mag

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.mag.compose.AlertDialogExample
import com.example.mag.ui.theme.MagTheme
import com.example.mag.utils.CheckForLocationPermission
import com.example.mag.utils.CheckServicePermission
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority



private lateinit var fusedLocationClient: FusedLocationProviderClient

private var requestingLocationUpdates by mutableStateOf(true)

    private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
    private const val MY_PERMISSIONS_REQUEST_BACKGROUND_LOCATION = 66



@Composable
fun StartTraining(navController: NavController, context: Context) {
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        CheckServicePermission()
        if (CheckForLocationPermission(context = context)) {
            ToggleLocationUpdatesButton(requestingLocationUpdates) {
                requestingLocationUpdates = !requestingLocationUpdates
                if (requestingLocationUpdates) {
                    val locationRequest = createLocationRequest()
                    startLocationUpdates(locationRequest, context = context)
                } else {
                    stopLocationUpdates()
                }
            }



            if (requestingLocationUpdates) {
                val locationRequest = createLocationRequest()
                startLocationUpdates(locationRequest, context)
            }
        }else{


            Button(
                onClick = {
                    requestLocationPermission(context as ComponentActivity)
                }
            ) {
                Text("Zezwól na lokalizację")
            }
        }
    }
}


    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY, 5000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(15000)
            .build()
    }

    private fun startLocationUpdates(locationRequest: LocationRequest, context: Context) {
        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.forEach { location ->

                    Log.d("Tag", "$location <- locationRequest")



                }
            }
        }


        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper())
        } else {
            Log.e("Location Updates", "Location permission not granted")
        }
    }



// Funkcja do wywołania, aby poprosić użytkownika o uprawnienia do lokalizacji
//private fun requestLocationPermission(activity: ComponentActivity) {
//    ActivityCompat.requestPermissions(
//        activity,
//        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
//        LOCATION_PERMISSION_REQUEST_CODE
//    )
//}

private fun checkLocationPermission(activity: ComponentActivity) {
    if (ActivityCompat.checkSelfPermission(
            activity,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.
            AlertDialog.Builder(activity)
                .setTitle("Location Permission Needed")
                .setMessage("This app needs the Location permission, please accept to use location functionality")
                .setPositiveButton(
                    "OK"
                ) { _, _ ->
                    //Prompt the user once explanation has been shown
                    requestLocationPermission(activity)
                }
                .create()
                .show()
        } else {
            // No explanation needed, we can request the permission.
            requestLocationPermission(activity)
        }
    } else {
        checkBackgroundLocation(activity)
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




private fun stopLocationUpdates() {
    // Do whatever is needed to stop location updates
}



@Composable
fun checkPlayService(): Boolean {
    val serviceResult = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LocalContext.current)
    return serviceResult == ConnectionResult.SUCCESS
}

@Composable
fun ToggleLocationUpdatesButton(requestingLocationUpdates:  Boolean, onClick: () -> Unit) {
    Button(
                onClick = onClick
    ) {
        Text(if (requestingLocationUpdates) "Stop Location Updates" else "Start Location Updates")
    }
}