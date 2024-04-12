package com.example.mag.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import com.example.mag.checkPlayService
import com.example.mag.compose.AlertDialogExample

@Composable
fun CheckServicePermission() {
    val playServicesAvailable = checkPlayService()
    if (!playServicesAvailable) {
        AlertDialogExample(
            dialogTitle = "You don't have play service",
            dialogText = "You can do nothing, bye bye",
            icon = Icons.Default.Info
        )
    }
}