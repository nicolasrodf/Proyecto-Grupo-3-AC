package com.app.projectgroup3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.projectgroup3.databinding.FragmentListPlacesBinding
import com.app.projectgroup3.model.Place
import com.app.projectgroup3.model.PlacesRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListPlacesFragment : Fragment(R.layout.fragment_list_places) {

    private val viewModel: ListPlacesViewModel by viewModels {
        ListPlacesViewModelFactory(
            PlacesRepository(requireActivity() as AppCompatActivity)
        )
    }
    private var adapter = PlacesAdapter { viewModel.onPlaceClicked(it) }
    private lateinit var binding: FragmentListPlacesBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentListPlacesBinding.bind(view)
        configRecyclerView()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { binding.updateUI(it) }
            }
        }
    }

    private fun FragmentListPlacesBinding.updateUI(state: ListPlacesViewModel.UiState) {
        progress.visibility = if (state.loading) View.VISIBLE else View.GONE
        state.places?.let(adapter::submitList)
        state.navigateTo?.let { navigateTo(it) }
    }

    private fun configRecyclerView() {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listenerModel(place: Place) {

    }

    private fun navigateTo(place: Place) {
        findNavController().navigate(
            R.id.action_listPlacesFragment_to_detailFragment,
            bundleOf("place" to place)
        )
    }
}