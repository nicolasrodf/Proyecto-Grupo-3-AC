package com.app.projectgroup3.model

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import com.app.projectgroup3.R
import java.lang.Exception
import java.util.ArrayList

class PlacesRepository(application: Application) {
    private val apiKey = application.getString(R.string.api_key)
    private val regionRepository = RegionRepository(application)

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