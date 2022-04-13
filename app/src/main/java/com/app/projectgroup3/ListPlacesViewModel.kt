package com.app.projectgroup3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.projectgroup3.model.Place
import com.app.projectgroup3.model.PlacesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListPlacesViewModel(
    private val placesRepository: PlacesRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(places = placesRepository.findPopularPlaces().results)
        }
    }

    fun onPlaceClicked(place: Place) {
        _state.value = _state.value.copy(navigateTo = place)
    }

    data class UiState(
        val loading: Boolean = false,
        val places: List<Place>? = null,
        val navigateTo: Place? = null
    )
}


@Suppress("UNCHECKED_CAST")
class ListPlacesViewModelFactory(
    private val placesRepository: PlacesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListPlacesViewModel(placesRepository) as T
    }
}