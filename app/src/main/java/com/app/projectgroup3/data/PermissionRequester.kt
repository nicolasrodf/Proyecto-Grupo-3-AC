package com.app.projectgroup3.data

import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.fragment.app.Fragment
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionRequester(fragment: Fragment, private val permission: String) {
    private var onRequest: (Boolean) -> Unit = {}
    private val launcher = fragment.registerForActivityResult(RequestPermission()) { isGranted ->
        onRequest(isGranted)
    }

    suspend fun request(): Boolean =
        suspendCancellableCoroutine { continuation ->
            onRequest = {
                continuation.resume(it)
            }
            launcher.launch(permission)
        }
}