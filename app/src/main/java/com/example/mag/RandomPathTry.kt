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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import com.example.mag.BuildConfig.MAPS_API_KEY
import com.example.mag.adapter.LatLngAdapter
import com.example.mag.path.checkLocationPermission
import com.example.mag.path.randomPoint
import com.example.mag.path.requestLocationPermission
import com.example.mag.utils.CheckServicePermission
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext

import com.google.maps.android.compose.GoogleMap
import com.google.maps.model.DirectionsResult
import com.google.maps.model.TravelMode
import kotlin.math.*
import kotlin.random.Random


private lateinit var fusedLocationClient: FusedLocationProviderClient

var lastLocation = LatLng(23.10, 21.0122)

private var points = mutableListOf<LatLng>()



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

                    points = randomPoint()




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





//                    pointsx.forEach { point ->
//                        val marker = MarkerOptions()
//                            .position(LatLng(point.latitude, point.longitude))
//                            .title("Point on map")
//                        googleMap.addMarker(marker)
//
//                    }


                    val googleMapsLatLng = points.map {
                        LatLngAdapter.toGoogleMapsLatLng(it)}

                    val contextNavi = GeoApiContext.Builder()
                        .apiKey(MAPS_API_KEY)
                        .build()

                    val result: DirectionsResult = DirectionsApi.newRequest(contextNavi)
                        .origin(com.google.maps.model.LatLng(lastLocation.latitude, lastLocation.longitude))
                        .destination(com.google.maps.model.LatLng(points.last().latitude, points.last().longitude))
                        .waypoints(*googleMapsLatLng.toTypedArray())
                        .mode(TravelMode.WALKING)
                        .await()


                    val route = result.routes[0]
                    val legs = route.legs
                    val polylineOptions = PolylineOptions()
                    for (leg in legs) {
                        val steps = leg.steps
                        for (step in steps) {
                            val points = step.polyline.decodePath()
                            for (point in points) {
                                polylineOptions.add(LatLng(point.lat, point.lng))

//                                val marker = MarkerOptions()
//                                    .position(LatLng(point.lat, point.lng))
//                                    .title("Point on route")
//                                googleMap.addMarker(marker)
                            }
                        }
                    }

                    val uniquePoints = mutableListOf<LatLng>()


                    for (leg in legs) {
                        val steps = leg.steps
                        for (step in steps) {
                            val points = step.polyline.decodePath()
                            for (point in points) {
                                val latLng = LatLng(point.lat, point.lng)
                                // Sprawdź, czy punkt już istnieje w liście uniquePoints
                                if (!uniquePoints.contains(latLng)) {
                                    // Dodaj punkt do listy unikalnych punktów
                                    uniquePoints.add(latLng)
                                }else{
                                    uniquePoints.remove(latLng)
                                }
                            }
                        }
                    }
//                    uniquePoints.forEach { point ->
//                        val marker = MarkerOptions()
//                            .position(LatLng(point.latitude, point.longitude))
//                            .title("Unique Point")
//                        googleMap.addMarker(marker)
//                    }

                    val newPolylineOptions = PolylineOptions()

                    uniquePoints.forEach { point ->
                        newPolylineOptions.add(point)
                    }

                    googleMap.addPolyline(newPolylineOptions)
                    


                }
            }
            mapView
        }

    )

}

