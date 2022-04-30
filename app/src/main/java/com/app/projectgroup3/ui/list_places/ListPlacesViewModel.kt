package com.app.projectgroup3.ui.list_places

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

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(places = placesRepository.findPopularPlaces().results)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val places: List<Place>? = null
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