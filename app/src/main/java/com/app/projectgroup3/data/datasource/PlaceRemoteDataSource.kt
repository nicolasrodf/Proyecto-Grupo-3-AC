package com.app.projectgroup3.data.datasource

import com.app.projectgroup3.data.RemoteConnection
import com.app.projectgroup3.data.RemoteResult

class PlaceRemoteDataSource(
    private val apiKey: String
) {
    suspend fun findPopularPlaces(region: String): RemoteResult {
        return try {
            RemoteConnection.service
                .listPopularMovies(
                    apiKey,
                    region
                )

        } catch (e: Exception) {
            RemoteResult(0, ArrayList(), 0, 0)
        }
    }
}