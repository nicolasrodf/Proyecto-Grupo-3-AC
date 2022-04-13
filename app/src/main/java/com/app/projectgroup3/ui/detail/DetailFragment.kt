package com.app.projectgroup3.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.app.projectgroup3.R
import com.app.projectgroup3.databinding.FragmentDetailBinding
import com.app.projectgroup3.loadUrl
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val safeArgs: DetailFragmentArgs by navArgs()
    private val viewModel: DetailViewModel by viewModels {
        DetailViewModelFactory(requireNotNull(safeArgs.place))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { binding.updateUI(it) }
            }
        }
    }

    private fun FragmentDetailBinding.updateUI(state: DetailViewModel.UiState) {
        val place = state.place
        movieDetailToolbar.title = place.name
        if (place.images.isNotEmpty()) {
            movieDetailImage.loadUrl(place.images[0])
        }
        /* movieDetailToolbar.title = movie.title
         movieDetailImage.loadUrl("https://image.tmdb.org/t/p/w780${movie.backdropPath}")
         movieDetailSummary.text = movie.overview
         movieDetailInfo.setMovie(movie)*/
    }
}