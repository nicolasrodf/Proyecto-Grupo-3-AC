package com.app.projectgroup3.data.datasource

import com.app.projectgroup3.data.database.Place
import com.app.projectgroup3.data.database.PlaceDao
import kotlinx.coroutines.flow.Flow

class PlaceLocalDataSource(private val placeDao: PlaceDao) {
    val places: Flow<List<Place>> = placeDao.getAll()

    suspend fun isEmpty(): Boolean = placeDao.placeCount() == 0

    fun findById(id: Int): Flow<Place> = placeDao.findById(id)

    suspend fun save(places: List<Place>) {
        placeDao.insertPlaces(places = places)
    }
}
