package com.app.projectgroup3.data

import com.app.projectgroup3.App
import com.app.projectgroup3.R
import com.app.projectgroup3.data.database.Place
import com.app.projectgroup3.data.datasource.PlaceLocalDataSource
import com.app.projectgroup3.data.datasource.PlaceRemoteDataSource
import com.google.gson.Gson
import com.app.projectgroup3.data.Place as RemotePlace

class PlacesRepository(application: App) {
    private val regionRepository = RegionRepository(application)
    private val localDataSource = PlaceLocalDataSource(application.db.placeDao())

    private val remoteDataSource = PlaceRemoteDataSource(
        application.getString(R.string.api_key)
    )

    val popularPlaces = localDataSource.places
    suspend fun requestPopularPlaces(): Error? = tryCall {
        if (localDataSource.isEmpty()) {
            val places = remoteDataSource.findPopularPlaces(regionRepository.findLastRegion())
            localDataSource.save(places.results.toLocalModel())
        }
    }
}

private fun List<RemotePlace>.toLocalModel(): List<Place> = map { it.toLocalModel() }
private fun RemotePlace.toLocalModel(): Place = Place(
    id = id,
    name = name,
    shortDescription = shortDescription,
    largeDescription = largeDescription,
    images = Gson().toJson(images),
    location = location,
    latitude = latitude,
    longitude = longitude,
    popularity = 0.0
)