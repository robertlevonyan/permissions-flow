package com.innfinity.permissionflow.lib

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import kotlinx.coroutines.flow.flow

object PermissionFlow {

    internal var permissionsToRequest: Array<out String> = emptyArray()
    internal var fragmentInFlow: Fragment? = null
    internal var activityInFlow: FragmentActivity? = null

    private var permissionFragment: PermissionFragment? = null

    fun request() = flow {
        createFragment()

        permissionFragment?.takeIf { permissionsToRequest.isNotEmpty() }?.run {
            request(*permissionsToRequest)
            val results = completableDeferred.await()
            if (results.isNotEmpty()) {
                emit(results.all { it.isGranted })
            }
        }
    }

    fun requestEach() = flow {
        createFragment()

        permissionFragment?.takeIf { permissionsToRequest.isNotEmpty() }?.run {
            request(*permissionsToRequest)
            val results = completableDeferred.await()
            results.forEach { emit(it) }
        }
    }

    private fun createFragment() = permissionFragment?.let {
        addFragment(it)
    } ?: PermissionFragment.newInstance().let {
        permissionFragment = it
        addFragment(it)
    }

    private fun addFragment(fragment: PermissionFragment) {
        val fragmentManager = activityInFlow?.supportFragmentManager
            ?: fragmentInFlow?.childFragmentManager
            ?: throw IllegalArgumentException("To work properly you need to pass an instance of a Fragment or a FragmentActivity")

        fragmentManager
            .beginTransaction()
            .apply {
                if (fragment.isAdded) {
                    detach(fragment)
                }
            }
            .add(fragment, PermissionFragment::class.java.simpleName)
            .commitNow()
    }

}

suspend fun permissionFlow(block: suspend PermissionFlow.() -> Unit) {
    block(PermissionFlow)
}

fun PermissionFlow.withPermissions(vararg permissions: String) {
    permissionsToRequest = permissions
}

fun PermissionFlow.withFragment(fragment: Fragment) {
    fragmentInFlow = fragment
}

fun PermissionFlow.withActivity(activity: FragmentActivity) {
    activityInFlow = activity
}