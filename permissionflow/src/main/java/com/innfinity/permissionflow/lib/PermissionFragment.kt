package com.innfinity.permissionflow.lib

import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CompletableDeferred

class PermissionFragment : Fragment() {
  var completableDeferred: CompletableDeferred<List<Permission>> = CompletableDeferred()

  private val permissionRequest = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
    if (permissions.all { it.value }) {
      completableDeferred.complete(permissions.map {
        Permission(
          permission = it.key,
          isGranted = it.value,
          shouldShowRational = showRequestPermissionRationale(it.key)
        )
      })
      completableDeferred = CompletableDeferred()
    }
  }

  fun request(vararg permissions: String) {
    permissionRequest.launch(permissions.toList().toTypedArray())
  }

  private fun showRequestPermissionRationale(permission: String) =
    activity?.let {
      !isPermissionGranted(permission) && ActivityCompat.shouldShowRequestPermissionRationale(it, permission)
    } ?: false

  private fun isPermissionGranted(permission: String): Boolean =
    activity?.let {
      PermissionChecker.checkSelfPermission(it, permission) == PermissionChecker.PERMISSION_GRANTED
    } ?: false

  override fun onDestroy() {
    super.onDestroy()
    if (completableDeferred.isActive) {
      completableDeferred.cancel()
    }
    completableDeferred = CompletableDeferred()
  }

  companion object {
    fun newInstance() = PermissionFragment()
  }
}
