package com.app.projectgroup3.ui.list_places

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.app.projectgroup3.data.Error
import com.app.projectgroup3.data.database.Place
import com.app.projectgroup3.data.toError
import com.app.projectgroup3.domain.GetPopularPlacesUseCase
import com.app.projectgroup3.domain.RequestPopularPlacesUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListPlacesViewModel(
    private val requestPopularPlacesUseCase: RequestPopularPlacesUseCase,
    private val getPopularPlacesUseCase: GetPopularPlacesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getPopularPlacesUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                .collect { places -> _state.update { UiState(places = places) } }
        }
    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
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


@Suppress("UNCHECKED_CAST")
class ListPlacesViewModelFactory(
    private val requestPopularPlacesUseCase: RequestPopularPlacesUseCase,
    private val getPopularPlacesUseCase: GetPopularPlacesUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListPlacesViewModel(
            getPopularPlacesUseCase = getPopularPlacesUseCase,
            requestPopularPlacesUseCase = requestPopularPlacesUseCase
        ) as T
    }
}