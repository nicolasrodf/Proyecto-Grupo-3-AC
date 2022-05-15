package com.app.projectgroup3.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("/list")
    suspend fun listPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("region") region: String
    ): RemoteResult

}