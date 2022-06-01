package com.app.data

import com.app.data.datasource.PlaceLocalDataSource
import com.app.data.datasource.PlaceRemoteDataSource
import com.app.domain.Error
import com.app.domain.Place
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PlacesRepository @Inject constructor(
    private val localDataSource: PlaceLocalDataSource,
    private val remoteDataSource: PlaceRemoteDataSource
) {
    fun getPopularPlaces() = localDataSource.places

    fun findById(id: Int): Flow<Place> = localDataSource.findByIdWithImages(id)

    suspend fun requestPopularMovies(): Error? {
        if (localDataSource.isEmpty()) {
            val places = remoteDataSource.findPopularPlaces()
            places.fold(ifLeft = { return it }) { localDataSource.save(it) }
        }
        return null
    }

    suspend fun switchFavorite(place: Place): Error? {
        val updatePlace = place.copy(favorite = !place.favorite)
        return localDataSource.save(listOf(updatePlace))
    }
}