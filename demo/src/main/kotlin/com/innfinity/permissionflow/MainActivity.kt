package com.innfinity.permissionflow

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.innfinity.permissionflow.databinding.ActivityMainBinding
import com.innfinity.permissionflow.lib.Permission
import com.innfinity.permissionflow.lib.requestEachPermissions
import com.innfinity.permissionflow.lib.requestPermissions
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
  private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    binding.tvState.text = "Permission results:"

    binding.btPermissionsAll.setOnClickListener {
      lifecycleScope.launch {
        requestPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
          .collect { permissions ->
            // check if all permissions have been granted
            // val allGranted = permissions.find { !it.isGranted } == null
            // or check them one by one
            permissions.forEach {
              appendInfo("[ALL]", it)
            }
          }
      }
    }
    binding.btPermissionsEach.setOnClickListener {
      lifecycleScope.launch {
        requestEachPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
          .collect { permission ->
            appendInfo("[EACH]", permission)
          }
      }
    }
  }

  private fun appendInfo(prefix: String, permission: Permission) {
    binding.tvState.text = "${binding.tvState.text}\n$prefix ${permission.permission.substringAfterLast(".")} = ${permission.isGranted}"
  }
}
