package com.app.projectgroup3.ui.list_places

import android.Manifest
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.projectgroup3.data.Error
import com.app.projectgroup3.data.PermissionRequester
import com.app.projectgroup3.data.database.Place
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import com.app.projectgroup3.R

fun Fragment.buildListPlacesState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
) = ListPlacesState(context, scope, navController, locationPermissionRequester)

class ListPlacesState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController,
    private val locationPermissionRequester: PermissionRequester
) {
    fun onPlaceClick(place: Place) {
        val action = ListPlacesFragmentDirections.actionListPlacesFragmentToDetailFragment(place)
        navController.navigate(action)
    }

    fun requestLocationPermission(afterRequest: (Boolean) -> Unit) {
        scope.launch {
            val result = locationPermissionRequester.request()
            afterRequest(result)
        }
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}