package com.app.projectgroup3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.projectgroup3.model.Place
import com.bumptech.glide.Glide

class PlaceAdapter(
    private val listener: (place: Place) -> Unit,
    private val context:Context
) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {
    private var list: List<Place> = ArrayList()

    fun setData(listAux: List<Place>) {
        this.list = listAux
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_place, parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class PlaceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title = view.findViewById<TextView>(R.id.titleTextView)
        private val description = view.findViewById<TextView>(R.id.descriptionTextView)
        private val image = view.findViewById<ImageView>(R.id.imageView)

        fun bind(place: Place) {
            title.text=place.name
            Glide.with(context)
                .load(place.image)
                .centerCrop()
                .into(image)

        }
    }
}