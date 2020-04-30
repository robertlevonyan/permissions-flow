package com.innfinity.permissionflow

import android.Manifest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.innfinity.permissionflow.lib.permissionFlow
import com.innfinity.permissionflow.lib.withActivity
import com.innfinity.permissionflow.lib.withPermissions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                permissionFlow {
                    withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                    withActivity(this@MainActivity)

                    //request all
//                    request().collect { granted ->
//                        println("PERMISSIONS $granted")
//                    }

                    //request sequentially
                    requestEach().collect { permission ->
                        println("PERMISSIONS $permission")
                    }
                }
            }
        }
    }
}
