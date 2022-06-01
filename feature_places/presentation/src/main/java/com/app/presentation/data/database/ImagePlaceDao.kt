package com.app.presentation.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ImagePlaceDao {
    @Query("SELECT *  FROM ImagePlace")
    fun getImagesOfPlace(): Flow<List<ImagePlace>>

    @Query("SELECT * FROM ImagePlace WHERE id=:id")
    fun findById(id: Int): Flow<ImagePlace>

    @Query("SELECT COUNT(id) FROM ImagePlace")
    suspend fun imagePlaceCountOfPlace(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageOfPlaces(imagesPlaces: List<ImagePlace>)

    @Update
    suspend fun updateImageOfPlace(imagePlace: ImagePlace)

}