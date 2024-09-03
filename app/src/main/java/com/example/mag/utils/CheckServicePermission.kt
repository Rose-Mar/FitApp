package com.example.mag.utils

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.mag.compose.AlertDialogExample
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

@Composable
fun CheckServicePermission() {
    val context = LocalContext.current
    val playServicesAvailable = remember { checkPlayService(context) }

    if (!playServicesAvailable) {
        AlertDialogExample(
            dialogTitle = "You don't have play service",
            dialogText = "This application requires Google Play Services to function properly. " +
                    "Please install or update Google Play Services and try again.",
            icon = Icons.Default.Info
        )
    }
}

fun checkPlayService(context: Context): Boolean {
    val googleApiAvailability = GoogleApiAvailability.getInstance()
    val resultCode = googleApiAvailability.isGooglePlayServicesAvailable(context)
    return resultCode == ConnectionResult.SUCCESS
}