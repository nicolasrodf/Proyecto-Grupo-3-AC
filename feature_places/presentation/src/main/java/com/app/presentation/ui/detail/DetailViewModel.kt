package com.app.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.Error
import com.app.domain.Place
import com.app.presentation.data.toError
import com.app.usecases.FindPlaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    findPlaceUseCase: FindPlaceUseCase
) : ViewModel() {

    private val placeId = DetailPlaceFragmentArgs.fromSavedStateHandle(savedStateHandle).idPlace

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findPlaceUseCase(placeId)
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { place ->
                    _state.update {
                        UiState(
                            place = place,
                            urlImage = place.images.random().url
                        )
                    }
                }
        }
    }


    data class UiState(
        val place: Place? = null,
        val urlImage: String? = null,
        val error: Error? = null
    )
}