package com.example.mag.compose

import android.content.Context
import androidx.compose.runtime.Composable
import com.example.mag.MapState
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLngBounds


@Composable
fun MapScreen(
    state: MapState,
//    setupClusterManager: (Context, GoogleMap) -> ZoneClusterManager,
    calculateZoneViewCentre: () -> LatLngBounds,

    ){


}