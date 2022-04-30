package com.app.projectgroup3.ui.list_places

import android.Manifest
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.projectgroup3.model.PermissionRequester
import com.app.projectgroup3.model.Place
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun Fragment.buildListPlacesState(
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController(),
    locationPermissionRequester: PermissionRequester = PermissionRequester(
        this,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )
) = ListPlacesState(scope, navController, locationPermissionRequester)

class ListPlacesState(
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
}