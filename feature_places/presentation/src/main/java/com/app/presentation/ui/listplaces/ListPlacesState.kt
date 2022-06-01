package com.app.presentation.ui.listplaces

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.app.domain.Error
import com.app.domain.Place
import com.app.presentation.R
import kotlinx.coroutines.CoroutineScope

fun Fragment.buildMainState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController()
) = ListPlacesState(context, scope, navController)

class ListPlacesState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController
) {
    fun onPlaceClicked(place: Place) {
        val action =
            ListPlacesFragmentDirections.actionListPlacesFragmentToDetailPlaceFragment(place.id)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }
}