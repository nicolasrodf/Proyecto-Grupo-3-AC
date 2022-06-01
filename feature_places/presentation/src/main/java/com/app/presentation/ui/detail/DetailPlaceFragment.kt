package com.app.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.app.presentation.R
import com.app.presentation.common.launchAndCollect
import com.app.presentation.databinding.FragmentDetailPlaceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPlaceFragment : Fragment(R.layout.fragment_detail_place) {
    private lateinit var binding: FragmentDetailPlaceBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailPlaceBinding.bind(view)
        initToolbar()
        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.place !=null){
                binding.place = state.place
                binding.urlImage=state.urlImage
            }
        }
    }

    private fun initToolbar() {
        val toolbar = binding.toolbar
        toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
    }
}