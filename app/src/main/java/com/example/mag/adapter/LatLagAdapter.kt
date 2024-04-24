package com.example.mag.adapter

import com.google.android.gms.maps.model.LatLng

class LatLngAdapter {
    companion object {
        fun toGoogleMapsLatLng(latLng: LatLng): com.google.maps.model.LatLng {
            return com.google.maps.model.LatLng(latLng.latitude, latLng.longitude)
        }

        fun toLatLng(googleMapsLatLng: com.google.maps.model.LatLng): LatLng {
            return LatLng(googleMapsLatLng.lat, googleMapsLatLng.lng)
        }
    }
}