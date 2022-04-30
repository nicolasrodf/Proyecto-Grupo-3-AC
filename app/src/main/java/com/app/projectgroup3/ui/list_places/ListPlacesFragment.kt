package com.app.projectgroup3.ui.list_places

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.projectgroup3.PlacesAdapter
import com.app.projectgroup3.R
import com.app.projectgroup3.databinding.FragmentListPlacesBinding
import com.app.projectgroup3.model.PlacesRepository
import com.app.projectgroup3.ui.common.launchAndCollect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class ListPlacesFragment : Fragment(R.layout.fragment_list_places) {

    private val viewModel: ListPlacesViewModel by viewModels {
        ListPlacesViewModelFactory(PlacesRepository(requireActivity().application))
    }
    private var adapter = PlacesAdapter { lisPlacesState.onPlaceClick(it) }
    private lateinit var lisPlacesState: ListPlacesState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lisPlacesState = buildListPlacesState()

        val binding = FragmentListPlacesBinding.bind(view).apply { recycler.adapter = adapter }

        with(viewModel.state) {
            diff({ it.places }, adapter::submitList)
            diff({ it.loading }) { binding.progress.isVisible = it }
        }

        lisPlacesState.requestLocationPermission { viewModel.onUiReady() }
    }

    private fun <T, U> Flow<T>.diff(mapf: (T) -> U, body: (U) -> Unit) {
        viewLifecycleOwner.launchAndCollect(
            flow = this.map(mapf).distinctUntilChanged(),
            body = body
        )
    }
}