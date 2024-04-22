package com.example.mag.compose

import android.location.Location
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

@Composable
fun MapViewContent() {

    val lastKnownLocation: Location?
    // Pamiętaj o odpowiednim ustawieniu klucza API Google Maps
    AndroidView(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        factory = { context ->
            val mapView = MapView(context).apply {
                // Inicjalizacja mapy
                onCreate(null)
                getMapAsync { googleMap ->
                    // Konfiguracja mapy, np. ustawienie punktu początkowego
                    val initialPosition = LatLng(52.2297, 21.0122)
                    val cameraPosition = CameraPosition.Builder()
                        .target(initialPosition)
                        .zoom(10f)
                        .build()
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
                }
            }
            mapView
        }
    )
}