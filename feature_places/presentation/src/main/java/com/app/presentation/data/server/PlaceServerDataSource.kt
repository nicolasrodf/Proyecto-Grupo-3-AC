package com.app.presentation.data.server

import arrow.core.Either
import com.app.data.datasource.PlaceRemoteDataSource
import com.app.domain.Error
import com.app.domain.ImagePlace
import com.app.domain.Place
import com.app.presentation.data.tryCall
import com.app.presentation.di.ApiKey
import javax.inject.Inject

class PlaceServerDataSource @Inject constructor(@ApiKey private val apiKey: String) :
    PlaceRemoteDataSource {
    override suspend fun findPopularPlaces(): Either<Error, List<Place>> = tryCall {
        RemoteConnection.service
            .listPopularMovies()
            .results.toDomainModel()
    }
}

private fun List<RemotePlace>.toDomainModel(): List<Place> = map { it.toDomainModel() }

private fun RemotePlace.toDomainModel() = Place(
    id,
    name,
    shortDescription,
    largeDescription,
    images.toDomainModel(),
    location,
    latitude,
    longitude,
    favorite = false
)

@JvmName("toDomainModelRemoteImagePlace")
private fun List<RemoteImagePlace>.toDomainModel(): List<ImagePlace> = map { it.toDomainModel() }

private fun RemoteImagePlace.toDomainModel() = ImagePlace(
    id,
    idPlace,
    url,
    position
)