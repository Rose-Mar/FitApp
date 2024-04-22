package com.example.mag

import android.Manifest
import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.mag.utils.CheckServicePermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlin.math.*
import kotlin.random.Random

private lateinit var fusedLocationClient: FusedLocationProviderClient
private const val MY_PERMISSIONS_REQUEST_LOCATION = 99
var lastLocation = LatLng(23.10, 21.0122)
private var pointsx = mutableListOf<LatLng>()


@Composable
fun RandomPathTry(navController: NavController, context: Context){



    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)


    CheckServicePermission()
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    lastLocation = LatLng(location.latitude,location.longitude)
                    val latitude = location.latitude
                    val longitude = location.longitude
                    Log.d(TAG, "Last location: $latitude, $longitude")

                    randomPoint()

                                } else {
                    Log.d(TAG, "No location available")
                  }
            }
            .addOnFailureListener { e ->
                 Log.e(TAG, "Error getting location: ${e.message}", e)
            }
    } else {
        Log.e(TAG, "No permission to access the location")
        requestLocationPermission(context)
        checkLocationPermission(context)
    }




    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        factory = { context ->
            val mapView = MapView(context).apply {
               onCreate(null)
                getMapAsync { googleMap ->
                    val initialPosition = lastLocation
                    val cameraPosition = CameraPosition.Builder()
                        .target(initialPosition)
                        .zoom(15f)
                        .build()
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

                    pointsx.forEach { point ->
                        val marker = MarkerOptions()
                            .position(LatLng(point.latitude, point.longitude))
                            .title("Point on map")
                        googleMap.addMarker(marker)

                    }
                }
            }
            mapView
        }

    )

}


private fun checkLocationPermission(context: Context) {
    if (context is ComponentActivity) {
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
        } else {

        }
    }
}


private fun requestLocationPermission(context: Context) {
    if (context is ComponentActivity) {
        ActivityCompat.requestPermissions(
            context,
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ),
            MY_PERMISSIONS_REQUEST_LOCATION
        )
    } else {
        Log.e(TAG, "Context is not an instance of ComponentActivity")
    }
}


fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val earthRadius = 6371.0
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a = sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
            sin(dLon / 2) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c
}

fun generatePointsInDistance(location: LatLng, distance: Double, numPoints: Int, initialDirectionAngle: Double): List<LatLng> {
    val points = mutableListOf<LatLng>()
    var currentLocation = location
    var directionAngle = initialDirectionAngle
    for (i in 1..numPoints) {
        directionAngle += Random.nextDouble(-60.0, 60.0)

        val lat = currentLocation.latitude + (distance / 111.0) * cos(Math.toRadians(directionAngle))
        val lon = currentLocation.longitude + (distance / (111.0 * cos(Math.toRadians(currentLocation.latitude)))) *
                sin(Math.toRadians(directionAngle))
        points.add(LatLng(lat, lon))
        currentLocation = LatLng(lat, lon)
    }
    return points
}



fun randomPoint() {
    val myLocation = lastLocation
    val targetDistance = 2.0
    val numPoints = ceil(targetDistance / 0.1).toInt()
    val directionAngle = Random.nextDouble() * 360.0

    val points = generatePointsInDistance(myLocation, 0.1, numPoints, directionAngle)
    var totalDistance = 0.0
    for (i in 0 until points.size - 1) {
        val distance = distanceBetweenPoints(
            points[i].latitude, points[i].longitude,
            points[i + 1].latitude, points[i + 1].longitude
        )
        totalDistance += distance
    }
    pointsx.addAll(points)
    Log.d(TAG, "Points count: ${points.size}")
    Log.d(TAG, "Distance: $totalDistance km")
}