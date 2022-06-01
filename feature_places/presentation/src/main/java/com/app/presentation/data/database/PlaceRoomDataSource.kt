package com.app.presentation.data.database

import android.util.Log
import com.app.data.datasource.PlaceLocalDataSource
import com.app.domain.Error
import com.app.domain.ImagePlace
import com.app.domain.Place
import com.app.presentation.data.tryCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.app.presentation.data.database.ImagePlace as DbImagePlace
import com.app.presentation.data.database.Place as DbPlace

class PlaceRoomDataSource @Inject constructor(
    private val placeDao: PlaceDao,
    private val imagePlaceDao: ImagePlaceDao
) :
    PlaceLocalDataSource {
    override val places: Flow<List<Place>> = placeDao.getAllPlacesWithImagePlaces().map { it.toDomainModel() }

    override suspend fun isEmpty(): Boolean = placeDao.placeCount() == 0

    override fun findById(id: Int): Flow<Place> = placeDao.findById(id).map { it.toDomainModel() }

    override fun findByIdWithImages(id: Int): Flow<Place> =
        placeDao.getPlaceWithImagePlaces(id).map { it.toDomainModel() }

    override suspend fun save(places: List<Place>): Error? = tryCall {
        placeDao.insertPlaces(places.fromDomainModel())
        places.map { imagePlaceDao.insertImageOfPlaces(it.images.fromDomainModel()) }
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
    location,
    latitude,
    longitude,
    favorite
)

@JvmName("toDomainModelPlaceWithImagePlaces")
private fun List<PlaceWithImagePlaces>.toDomainModel(): List<Place> = map {it.toDomainModel()}

private fun PlaceWithImagePlaces.toDomainModel(): Place {
    val placeModel = this.place.toDomainModel()
    placeModel.images = this.listOfImages.toDomainModels()
    return placeModel
}

private fun List<Place>.fromDomainModel(): List<DbPlace> = map { it.fromDomainModel() }

private fun Place.fromDomainModel() = DbPlace(
    id,
    name,
    shortDescription,
    largeDescription,
    location,
    latitude,
    longitude,
    favorite
)

private fun List<DbImagePlace>.toDomainModels(): List<ImagePlace> = map { it.toDomainModel() }


private fun DbImagePlace.toDomainModel() = ImagePlace(
    id = id,
    idPlace = idPlace,
    url = url,
    position = position
)


@JvmName("fromDomainModelImagePlace")
private fun List<ImagePlace>.fromDomainModel(): List<DbImagePlace> = map { it.fromDomainModel() }

private fun ImagePlace.fromDomainModel() = DbImagePlace(
    id = id,
    idPlace = idPlace,
    url = url,
    position = position
)