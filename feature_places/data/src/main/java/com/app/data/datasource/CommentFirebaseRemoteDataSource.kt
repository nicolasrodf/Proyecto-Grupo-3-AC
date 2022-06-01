package com.app.data.datasource

import arrow.core.Either
import com.app.domain.Comment
import com.app.domain.Error
import kotlinx.coroutines.flow.Flow

interface CommentFirebaseRemoteDataSource {
    suspend fun getCommentOfPlace(idPlace:Int): Either<Error,Flow<List<Comment>>>
}