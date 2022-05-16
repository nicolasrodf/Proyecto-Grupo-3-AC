package com.app.presentation.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {
    @GET("/list")
    suspend fun listPopularMovies(): RemoteResult
}