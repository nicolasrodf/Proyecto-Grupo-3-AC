package com.app.projectgroup3.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.projectgroup3.data.database.Place
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel(place: Place) : ViewModel() {
    class UiState(val place: Place)

    private val _state = MutableStateFlow(UiState(place))
    val state: StateFlow<UiState> = _state.asStateFlow()
}

@Suppress("UNCHECKED_CAST")
class DetailViewModelFactory(private val place: Place) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailViewModel(place) as T
    }
}