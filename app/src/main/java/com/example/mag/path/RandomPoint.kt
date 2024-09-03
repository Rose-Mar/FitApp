package com.example.mag.path

import com.example.mag.lastLocation
import com.google.android.gms.maps.model.LatLng
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.random.Random


private var pointsx = mutableListOf<LatLng>()

fun distanceBetweenPoints(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val earthRadius = 6371.0
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a =
        sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) *
                cos(Math.toRadians(lat2)) * sin(
            dLon / 2
        ) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c
}

fun generatePointsInDistance(
    location: LatLng, totalDistance: Double, numPoints: Int
): List<LatLng> {
    val points = mutableListOf<LatLng>()
    var currentLocation = location
    val segmentDistance = totalDistance / numPoints

    for (i in 1..numPoints) {
        var newPoint: LatLng
        do {
            val directionAngle = Random.nextDouble(-60.0, 60.0)
            val lat = currentLocation.latitude + (segmentDistance / 111.0) * cos(
                Math.toRadians(directionAngle)
            )
            val lon = currentLocation.longitude + (segmentDistance / (111.0 * cos(
                Math.toRadians(currentLocation.latitude)
            ))) * sin(Math.toRadians(directionAngle))
            newPoint = LatLng(lat, lon)
        } while (points.contains(newPoint))

        points.add(newPoint)
        currentLocation = newPoint
    }
    return points
}

fun randomPoint(distance: Int): MutableList<LatLng> {
    val myLocation = lastLocation
    val numPoints = 10
    val points = generatePointsInDistance(myLocation, distance.toDouble(), numPoints)

    pointsx.clear()
    pointsx.addAll(points)

    return pointsx
}


private fun calculateTotalDistance(points: List<LatLng>): Double {
    var totalDistance = 0.0
    for (i in 0 until points.size - 1) {
        totalDistance += calculateDistance(
            points[i].latitude, points[i].longitude, points[i + 1].latitude, points[i + 1].longitude
        )
    }
    return totalDistance
}

private fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
    val earthRadius = 6371.0
    val dLat = Math.toRadians(lat2 - lat1)
    val dLon = Math.toRadians(lon2 - lon1)
    val a =
        sin(dLat / 2) * sin(dLat / 2) + cos(Math.toRadians(lat1)) *
                cos(Math.toRadians(lat2)) * sin(
            dLon / 2
        ) * sin(dLon / 2)
    val c = 2 * atan2(sqrt(a), sqrt(1 - a))
    return earthRadius * c
}
