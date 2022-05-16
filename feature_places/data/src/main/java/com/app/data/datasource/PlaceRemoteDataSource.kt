package com.app.data.datasource

import arrow.core.Either
import com.app.domain.Error
import com.app.domain.Place

interface PlaceRemoteDataSource {
    suspend fun findPopularPlaces(): Either<List<Place>,Error>
}