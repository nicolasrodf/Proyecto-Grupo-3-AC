package com.app.presentation.data.firebase

import arrow.core.Either
import com.app.data.datasource.CommentFirebaseRemoteDataSource
import com.app.domain.Comment
import com.app.domain.Error
import com.app.domain.Place
import com.app.presentation.data.tryCall
import com.app.presentation.di.ApiKey
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CommentFirebaseServerDataSource @Inject constructor(@ApiKey private val apiKey: String) : CommentFirebaseRemoteDataSource {
    override suspend fun getCommentOfPlace(idPlace:Int): Either<Error, Flow<List<Comment>>> = tryCall {
        callbackFlowComments(idPlace)
    }

    private fun callbackFlowComments(idPlace:Int):Flow<List<Comment>> = callbackFlow {
        val databaseFirebase = Firebase.database.reference.child("comments")
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listComments = ArrayList<RemoteComment>()
                for (postSnapshot in snapshot.children) {
                    val commentModel = postSnapshot.getValue(RemoteComment::class.java)
                    listComments.add(commentModel!!)
                }
                trySend(listComments.toDomain())
            }

            override fun onCancelled(error: DatabaseError) {
                trySend(ArrayList())
            }
        }
        databaseFirebase.addValueEventListener(listener)
        awaitClose {
            databaseFirebase.removeEventListener(listener)
        }
    }
}

fun RemoteComment.toDomain() = Comment(
    id,
    idPlace,
    idUser,
    timeRegister,
    nameUser,
    commentText
)

fun List<RemoteComment>.toDomain() = map { it.toDomain() }