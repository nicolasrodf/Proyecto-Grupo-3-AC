package com.app.usecases

import com.app.data.CommentsRepository
import com.app.domain.Comment
import com.app.domain.Error
import com.app.domain.Place
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveCommentOfPlaceUseCase @Inject constructor(private val repository: CommentsRepository) {
    operator fun invoke(comment: Comment): Flow<Error?> = repository.saveCommentOfPlace(comment)
}