package com.app.projectgroup3

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.projectgroup3.model.Place

class ListPlacesActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    private var adapter = PlacesAdapter { Log.d("", "" + it.name) }
    private val listPlaces = ArrayList<Place>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_places)
        recyclerView = findViewById(R.id.recyclerView)
        configRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun configRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        recyclerView.adapter = adapter

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun listenerModel(place: Place) {

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadData() {
        adapter.submitList(getData())
    }

    private fun getData(): List<Place> = listOf(
        Place(
            id = "1",
            name = "Machu Picchu",
            shortDescription = getString(R.string.medium_lorem_ipsum),
            largeDescription = getString(R.string.long_lorem_ipsum_2),
            image = "https://viajes.nationalgeographic.com.es/medio/2019/06/06/macu_5da3ead6_1200x630.jpg",
            location = "1234545",
            latitude = 1234545.0,
            longitude = 123123.2
        ),
        Place(
            id = "2",
            name = "Machu Picchu2",
            shortDescription = getString(R.string.medium_lorem_ipsum),
            largeDescription = getString(R.string.long_lorem_ipsum_2),
            image = "https://viajes.nationalgeographic.com.es/medio/2019/06/06/macu_5da3ead6_1200x630.jpg",
            location = "1234545",
            latitude = 1234545.0,
            longitude = 123123.2
        ),
        Place(
            id = "3",
            name = "Machu Picchu3",
            shortDescription = getString(R.string.medium_lorem_ipsum),
            largeDescription = getString(R.string.long_lorem_ipsum_2),
            image = "https://viajes.nationalgeographic.com.es/medio/2019/06/06/macu_5da3ead6_1200x630.jpg",
            location = "1234545",
            latitude = 1234545.0,
            longitude = 123123.2
        )
    )
}