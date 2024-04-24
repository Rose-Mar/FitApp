package com.example.mag.path

import android.content.ContentValues
import android.util.Log
import com.example.mag.lastLocation
import com.google.android.gms.maps.model.LatLng
import kotlin.math.atan2
import kotlin.math.ceil
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


private var pointsx = mutableListOf<LatLng>()



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



fun randomPoint(): MutableList<LatLng> {
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


    Log.d(ContentValues.TAG, "Points count: ${points.size}")
    Log.d(ContentValues.TAG, "Distance: $totalDistance km")



    return pointsx
}
