package com.app.usecases

import com.app.data.PlacesRepository
import com.app.domain.Place
import javax.inject.Inject

class SwitchPlaceFavoriteUseCase  @Inject constructor(private val placesRepository: PlacesRepository) {
    suspend operator fun invoke(place: Place) = placesRepository.switchFavorite(place)
}