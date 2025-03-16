package com.innfinity.compose.permissions

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

class PermissionState internal constructor(
    private val permissions: List<String>,
    private val launcher: ManagedActivityResultLauncher<Array<String>, Map<String, @JvmSuppressWildcards Boolean>>,
) {
    fun launchPermissionRequest() = launcher.launch(permissions.toTypedArray())
}

@Composable
fun rememberPermissionState(
    permissions: List<String>,
    onResult: (Boolean) -> Unit,
): PermissionState {
    if (permissions.isEmpty()) {
        throw IllegalStateException("At least one permission is required.")
    }
    val permissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestMultiplePermissions(),
            onResult = {
                onResult(it.values.all { isGranted -> isGranted })
            },
        )

    return PermissionState(
        permissions = permissions,
        launcher = permissionLauncher
    )
}
