package com.innfinity.permissionflow.lib

data class Permission(
    val permission: String,
    val isGranted: Boolean,
    val shouldShowRational: Boolean = false
)
