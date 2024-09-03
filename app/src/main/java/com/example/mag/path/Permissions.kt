package com.example.mag.path

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.app.ActivityCompat

private const val MY_PERMISSIONS_REQUEST_LOCATION = 99

fun checkLocationPermission(context: Context) {
    if (context is ComponentActivity) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    context, Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                AlertDialog.Builder(context).setTitle("Location Permission Needed")
                    .setMessage("This app needs the Location permission, please accept " +
                            "to use location functionality")
                    .setPositiveButton(
                        "OK"
                    ) { _, _ ->

                        requestLocationPermission(context)
                    }.create().show()
            } else {

                requestLocationPermission(context)
            }
        }
    }
}

fun requestLocationPermission(context: Context) {
    if (context is ComponentActivity) {
        ActivityCompat.requestPermissions(
            context, arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
            ), MY_PERMISSIONS_REQUEST_LOCATION
        )
    } else {
        Toast.makeText(context, "Permission needed to access location",
            Toast.LENGTH_SHORT).show()
    }
}

