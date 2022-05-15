package com.app.projectgroup3.ui.list_places

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.projectgroup3.PlacesAdapter
import com.app.projectgroup3.data.database.Place

@BindingAdapter("items")
fun RecyclerView.setItems(places: List<Place>?) {
    if (places != null) {
        (adapter as? PlacesAdapter)?.submitList(places)
    }
}