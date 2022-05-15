package com.app.projectgroup3.domain

import com.app.projectgroup3.data.Error
import com.app.projectgroup3.data.PlacesRepository

class RequestPopularPlacesUseCase(private val placesRepository: PlacesRepository) {
    suspend operator fun invoke(): Error? = placesRepository.requestPopularPlaces()
}