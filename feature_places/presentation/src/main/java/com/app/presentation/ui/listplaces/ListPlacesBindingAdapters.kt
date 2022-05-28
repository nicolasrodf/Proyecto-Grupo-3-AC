package com.app.presentation.ui.listplaces

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.domain.Place

@BindingAdapter("items")
fun RecyclerView.setItems(places: List<Place>?) {
    if (places != null) {
        (adapter as? PlacesAdapter)?.submitList(places)
    }
}