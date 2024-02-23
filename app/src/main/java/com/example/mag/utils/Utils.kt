package com.example.mag.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
//import com.example.mag.Manifest

fun checkForPermission(context: Context): Boolean{
    return !(ActivityCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED
    )
}