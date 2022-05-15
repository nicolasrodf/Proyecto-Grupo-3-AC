package com.app.projectgroup3.domain

import com.app.projectgroup3.data.PlacesRepository
import com.app.projectgroup3.data.database.Place
import kotlinx.coroutines.flow.Flow

class GetPopularPlacesUseCase(private val repository: PlacesRepository) {
    operator fun invoke(): Flow<List<Place>> = repository.popularPlaces
}