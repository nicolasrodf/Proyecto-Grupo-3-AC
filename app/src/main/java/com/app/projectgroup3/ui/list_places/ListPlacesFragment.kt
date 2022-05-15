package com.app.projectgroup3.ui.list_places

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.projectgroup3.PlacesAdapter
import com.app.projectgroup3.R
import com.app.projectgroup3.data.PlacesRepository
import com.app.projectgroup3.databinding.FragmentListPlacesBinding
import com.app.projectgroup3.domain.GetPopularPlacesUseCase
import com.app.projectgroup3.domain.RequestPopularPlacesUseCase
import com.app.projectgroup3.ui.common.app
import com.app.projectgroup3.ui.common.launchAndCollect

class ListPlacesFragment : Fragment(R.layout.fragment_list_places) {

    private val viewModel: ListPlacesViewModel by viewModels {
        val repository = PlacesRepository(requireActivity().app)
        ListPlacesViewModelFactory(
            getPopularPlacesUseCase = GetPopularPlacesUseCase(repository),
            requestPopularPlacesUseCase = RequestPopularPlacesUseCase(repository)
        )
    }
    private var adapter = PlacesAdapter { lisPlacesState.onPlaceClick(it) }
    private lateinit var lisPlacesState: ListPlacesState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lisPlacesState = buildListPlacesState()

        val binding = FragmentListPlacesBinding.bind(view).apply { recycler.adapter = adapter }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.places = it.places
            binding.error = it.error?.let(lisPlacesState::errorToString)
        }

        lisPlacesState.requestLocationPermission { viewModel.onUiReady() }
    }
}