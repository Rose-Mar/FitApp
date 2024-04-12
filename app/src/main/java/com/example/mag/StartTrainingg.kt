//package com.example.mag
//
//import android.Manifest
//import android.content.pm.PackageManager
//import android.os.Bundle
//import android.os.Looper
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Info
//import androidx.compose.material3.Button
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.core.content.ContextCompat
//import com.example.mag.compose.AlertDialogExample
//import com.example.mag.ui.theme.MagTheme
//import com.google.android.gms.common.ConnectionResult
//import com.google.android.gms.common.GoogleApiAvailability
//import com.google.android.gms.location.FusedLocationProviderClient
//import com.google.android.gms.location.LocationCallback
//import com.google.android.gms.location.LocationRequest
//import com.google.android.gms.location.LocationResult
//import com.google.android.gms.location.LocationServices
//import com.google.android.gms.location.Priority
//
//
//
//private lateinit var fusedLocationClient: FusedLocationProviderClient
//
//
//
//
//class StartTrainingg : ComponentActivity() {
//
//    private var requestingLocationUpdates by mutableStateOf(true)
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
//
//
//
//        setContent {
//            MagTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    CheckServicePermission()
//                    ToggleLocationUpdatesButton(requestingLocationUpdates) {
//                        requestingLocationUpdates = !requestingLocationUpdates
//                        if (requestingLocationUpdates) {
//                            val locationRequest = createLocationRequest()
//                            startLocationUpdates(locationRequest)
//                        } else {
//                            stopLocationUpdates()
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (requestingLocationUpdates) {
//            val locationRequest = createLocationRequest()
//            startLocationUpdates(locationRequest)
//        }
//    }
//
//
//    private fun createLocationRequest(): LocationRequest {
//        return LocationRequest.Builder(
//            Priority.PRIORITY_HIGH_ACCURACY, 5000)
//            .setWaitForAccurateLocation(false)
//            .setMinUpdateIntervalMillis(2000)
//            .setMaxUpdateDelayMillis(15000)
//            .build()
//    }
//
//    private fun startLocationUpdates(locationRequest: LocationRequest) {
//        val locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult) {
//                locationResult.locations.forEach { location ->
//
//                    Log.d("Tag", "$location <- locationRequest")
//
//
//
//                }
//            }
//        }
//
//
//        if (ContextCompat.checkSelfPermission(
//                this,
//                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
//        ) {
//            fusedLocationClient.requestLocationUpdates(
//                locationRequest,
//                locationCallback,
//                Looper.getMainLooper())
//        } else {
//            Log.e("Location Updates", "Location permission not granted")
//        }
//    }
//}
//
//private fun stopLocationUpdates() {
//    // Do whatever is needed to stop location updates
//}

//@Composable
//fun CheckServicePermission() {
//    val playServicesAvailable = checkPlayService()
//    if (!playServicesAvailable) {
//        AlertDialogExample(
//            dialogTitle = "You don't have play service",
//            dialogText = "You can do nothing, bye bye",
//            icon = Icons.Default.Info
//        )
//    }
//}
//
//@Composable
//fun checkPlayService(): Boolean {
//    val serviceResult = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(LocalContext.current)
//    return serviceResult == ConnectionResult.SUCCESS
//}
//
//@Composable
//fun ToggleLocationUpdatesButton(requestingLocationUpdates:  Boolean, onClick: () -> Unit) {
//    Button(
//                onClick = onClick
//    ) {
//        Text(if (requestingLocationUpdates) "Stop Location Updates" else "Start Location Updates")
//    }
//}