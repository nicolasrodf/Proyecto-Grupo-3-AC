package com.app.presentation.listplaces

import androidx.lifecycle.ViewModel
import com.app.domain.Error
import com.app.domain.Place
import com.app.usecases.GetPopularPlacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ListPlacesViewModel @Inject constructor(
    //getPopularPlacesUseCase: GetPopularPlacesUseCase,
    // private val requestPopularPlacesUseCase: RequestPopularPlacesUseCase
) : ViewModel() {
    //private val _state = MutableStateFlow(UiState())
    //val state: StateFlow<UiState> = _state.asStateFlow()

    /*
        init {
            viewModelScope.launch {
                getPopularPlacesUseCase()
                    .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                    .collect { places -> _state.update { UiState(places = places) } }
            }
        }
    */
    data class UiState(
        val loading: Boolean = false,
        val places: List<Place>? = null,
        val error: Error? = null
    )
}
