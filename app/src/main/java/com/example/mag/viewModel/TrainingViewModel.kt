package com.example.mag.viewModel

import android.app.Application
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.lifecycle.AndroidViewModel
import com.google.android.gms.location.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import android.Manifest
import com.example.mag.database.TrainingDao

class TrainingViewModel(application: Application, private val trainingDao: TrainingDao) : AndroidViewModel(application) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(application)

    private val _location = MutableStateFlow<Location?>(null)
    val location: StateFlow<Location?> = _location

    private val _time = MutableStateFlow(0L)
    val time: StateFlow<Long> = _time

    private val _distance = MutableStateFlow(0f)
    val distance: StateFlow<Float> = _distance

    private val _calories = MutableStateFlow(0f)
    val calories: StateFlow<Float> = _calories

    private val _speed = MutableStateFlow(0f)
    val speed: StateFlow<Float> = _speed

    var requestingLocationUpdates = MutableStateFlow(false)
    private var locationCallback: LocationCallback? = null

    fun toggleLocationUpdates() {
        if (requestingLocationUpdates.value) {
            stopLocationUpdates()
        } else {
            startLocationUpdates()
        }
    }

    fun startLocationUpdates() {
        requestingLocationUpdates.value = true
        val locationRequest = LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY,
            5000)
            .setWaitForAccurateLocation(false)
            .setMinUpdateIntervalMillis(2000)
            .setMaxUpdateDelayMillis(15000)
            .build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    _location.value = location
                    Log.d("TrainingViewModel", "Current Location: " +
                            "Lat=${location.latitude}, Lon=${location.longitude}")
                }
            }
        }
        if (ActivityCompat.checkSelfPermission(getApplication(),
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback!!,
                Looper.getMainLooper())
        }
    }

    private fun stopLocationUpdates() {
        requestingLocationUpdates.value = false
        locationCallback?.let {
            fusedLocationClient.removeLocationUpdates(it)
        }
    }
}
