package com.app.presentation.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.domain.Comment
import com.app.domain.Error
import com.app.domain.Place
import com.app.presentation.data.toError
import com.app.presentation.ui.common.getDateTime
import com.app.usecases.FindPlaceUseCase
import com.app.usecases.GetCommentsOfPlaceUseCase
import com.app.usecases.SaveCommentOfPlaceUseCase
import com.app.usecases.SwitchPlaceFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    findPlaceUseCase: FindPlaceUseCase,
    getCommentsOfPlaceUseCase: GetCommentsOfPlaceUseCase,
    private val saveCommentOfPlaceUseCase: SaveCommentOfPlaceUseCase,
    private val switchPlaceFavoriteUseCase: SwitchPlaceFavoriteUseCase
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
        viewModelScope.launch {
            val comments = getCommentsOfPlaceUseCase.invoke(placeId)
            comments.fold(ifLeft = { errorModel ->
                _state.update { UiState(error = errorModel) }
            }) { flowComments ->
                flowComments
                    .catch { cause -> _state.update { it.copy(error = cause.toError()) } }
                    .collect { comments ->
                        _state.update {
                            _state.value.copy(
                                comments = comments,
                            )
                        }
                    }
            }
        }
    }

    fun onFavoriteClicked() {
        viewModelScope.launch {
            _state.value.place?.let { place ->
                val error = switchPlaceFavoriteUseCase(place)
                _state.update { it.copy(error = error) }
            }
        }
    }

    fun saveCommentClicked(textComment: String) {
        val comment = Comment(
            id = "",
            idPlace = placeId,
            commentText = textComment,
            nameUser = "Albert Montes",
            timeRegister = getDateTime(),
            idUser = 1
        )
        viewModelScope.launch {
            saveCommentOfPlaceUseCase(comment).catch { cause ->
                _state.update { it.copy(error = cause.toError()) }
            }.collect {

            }
        }
    }
}

data class UiState(
    val place: Place? = null,
    val urlImage: String? = null,
    val comments: List<Comment>? = null,
    val error: Error? = null
)