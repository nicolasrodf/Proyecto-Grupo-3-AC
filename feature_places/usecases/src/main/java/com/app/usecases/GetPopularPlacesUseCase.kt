package com.app.usecases

import com.app.data.PlacesRepository
import javax.inject.Inject

class GetPopularPlacesUseCase  constructor(private val repository: PlacesRepository) {
    operator fun invoke() = repository.getPopularPlaces()
}