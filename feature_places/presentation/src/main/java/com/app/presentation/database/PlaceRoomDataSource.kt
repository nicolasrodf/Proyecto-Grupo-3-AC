package com.app.presentation.database

import com.app.data.datasource.PlaceLocalDataSource
import com.app.domain.Error
import com.app.domain.Place
import com.app.presentation.data.tryCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.app.presentation.database.Place as DbPlace

class PlaceRoomDataSource @Inject constructor(private val placeDao: PlaceDao) :
    PlaceLocalDataSource {
    override val places: Flow<List<Place>> = placeDao.getAll().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = placeDao.placeCount() == 0

    override fun findById(id: Int): Flow<Place> = placeDao.findById(id).map { it.toDomainModel() }

    override suspend fun save(places: List<Place>): Error? = tryCall {
        placeDao.insertPlaces(places.fromDomainModel())
    }.fold(
        ifLeft = { it },
        ifRight = { null }
    )
}

private fun List<DbPlace>.toDomainModel(): List<Place> = map { it.toDomainModel() }

private fun DbPlace.toDomainModel() = Place(
    id,
    name,
    shortDescription,
    largeDescription,
    ArrayList(),
    image,
    location,
    latitude,
    longitude,
    favorite
)

private fun List<Place>.fromDomainModel(): List<DbPlace> = map { it.fromDomainModel() }

private fun Place.fromDomainModel() = DbPlace(
    id,
    name,
    shortDescription,
    largeDescription,
    image,
    location,
    latitude,
    longitude,
    favorite
)