package com.app.data

import arrow.core.Either
import com.app.data.datasource.CommentFirebaseRemoteDataSource
import com.app.domain.Comment
import com.app.domain.Error
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CommentsRepository @Inject constructor(
    private val commentFirebaseRemoteDataSource: CommentFirebaseRemoteDataSource
) {
    suspend fun getCommentsOfPlace(idPlace: Int): Either<Error, Flow<List<Comment>>> =
        commentFirebaseRemoteDataSource.getCommentsOfPlace(idPlace)

    fun saveCommentOfPlace(comment: Comment): Flow<Error?> =
        commentFirebaseRemoteDataSource.saveCommentOfPlace(comment)
}