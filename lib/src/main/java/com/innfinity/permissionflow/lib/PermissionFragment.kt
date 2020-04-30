package com.innfinity.permissionflow.lib

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CompletableDeferred

class PermissionFragment : Fragment() {
    companion object {
        private const val REQUEST_PERMISSION = 10

        fun newInstance() = PermissionFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun request(vararg permissions: String) {
        requestPermissions(permissions, REQUEST_PERMISSION)
    }

    var completableDeferred: CompletableDeferred<List<Permission>> = CompletableDeferred()

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != REQUEST_PERMISSION) return
        completableDeferred.complete(permissions.map {
            Permission(
                it,
                isPermissionGranted(it),
                showRequestPermissionRationale(it)
            )
        })
        completableDeferred = CompletableDeferred()
    }

    private fun showRequestPermissionRationale(permission: String) =
        activity?.run {
            !isPermissionGranted(permission) && shouldShowRequestPermissionRationale(permission)
        } ?: false

    private fun isPermissionGranted(permission: String): Boolean =
        activity?.run { checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED }
            ?: false

    override fun onDestroy() {
        super.onDestroy()
        if (completableDeferred.isActive) {
            completableDeferred.cancel()
        }
    }

}