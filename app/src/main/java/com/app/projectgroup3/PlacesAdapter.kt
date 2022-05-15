package com.app.projectgroup3

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.projectgroup3.databinding.ViewPlaceBinding
import com.app.projectgroup3.data.database.Place
import com.app.projectgroup3.ui.common.basicDiffUtil
import com.app.projectgroup3.ui.common.inflate
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlacesAdapter(private val listener: (Place) -> Unit) :
    ListAdapter<Place, PlacesAdapter.ViewHolder>(basicDiffUtil { old, new -> old.id == new.id }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.view_place, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val place = getItem(position)
        holder.bind(place)
        holder.itemView.setOnClickListener { listener(place) }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewPlaceBinding.bind(view)
        fun bind(place: Place) = with(binding) {
            titleTextView.text = place.name
            descriptionTextView.text = place.shortDescription
            val list: List<String> = Gson().fromJson(
                place.images,
                object : TypeToken<List<String>>() {}.type
            )

            if (place.images.isNotEmpty()) {
                imageView.loadUrl(
                    list[0]
                )
            }
        }
    }
}