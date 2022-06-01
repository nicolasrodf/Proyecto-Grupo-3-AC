package com.app.presentation.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query("SELECT * FROM Place")
    fun getAll(): Flow<List<Place>>

    @Query("SELECT * FROM Place WHERE id=:id")
    fun findById(id: Int): Flow<Place>

    @Query("SELECT COUNT(id) FROM Place")
    suspend fun placeCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlaces(places: List<Place>)

    @Update
    suspend fun updatePlace(place: Place)

    @Transaction
    @Query("SELECT * FROM Place where id=:idPlace")
    fun getPlaceWithImagePlaces(idPlace: Int): Flow<PlaceWithImagePlaces>

    @Transaction
    @Query("SELECT * FROM Place")
    fun getAllPlacesWithImagePlaces(): Flow<List<PlaceWithImagePlaces>>

}
