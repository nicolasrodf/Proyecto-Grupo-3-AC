package com.app.presentation.ui.listplaces

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.presentation.R
import com.app.presentation.common.launchAndCollect
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListPlacesFragment : Fragment(R.layout.fragment_main_list_places) {
    private val viewModel: ListPlacesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            Log.d("LOG XD", "0" + Gson().toJson(it))
        }
    }
}