package com.app.usecases

import com.app.data.PlacesRepository
import com.app.domain.Place
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindPlaceUseCase @Inject constructor(private val repository: PlacesRepository) {
    operator fun invoke(id: Int): Flow<Place> = repository.findById(id)
}