package com.app.projectgroup3.model

import androidx.appcompat.app.AppCompatActivity
import com.app.projectgroup3.R
import java.lang.Exception
import java.util.ArrayList

class PlacesRepository(activity:AppCompatActivity) {
    private val apiKey = activity.getString(R.string.api_key)
    private val regionRepository = RegionRepository(activity)

    suspend fun findPopularPlaces() :RemoteResult{
        try {
            val result = RemoteConnection.service
                .listPopularMovies(
                    apiKey,
                    regionRepository.findLastRegion()
                )
            return result

        }catch (e:Exception){
            return RemoteResult(0,ArrayList(),0,0)

        }
    }
}