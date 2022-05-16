package com.app.presentation.listplaces

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.Error
import com.app.domain.Place
import com.app.presentation.data.toError
import com.app.usecases.GetPopularPlacesUseCase
import com.app.usecases.RequestPopularPlacesUseCase
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListPlacesViewModel @Inject constructor(
    getPopularPlacesUseCase: GetPopularPlacesUseCase,
    private val requestPopularPlacesUseCase: RequestPopularPlacesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {

        onUiReady()
        /*viewModelScope.launch {
            Log.d("LOG XD", "1")
            getPopularPlacesUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { places ->
                    _state.update {
                        Log.d("LOG XD", "" + Gson().toJson(it.places))
                        UiState(places = places)
                    }
                }
        }*/
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true)
            val error = requestPopularPlacesUseCase()
            _state.update { _state.value.copy(loading = false, error = error) }
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val places: List<Place>? = null,
        val error: Error? = null
    )
}
