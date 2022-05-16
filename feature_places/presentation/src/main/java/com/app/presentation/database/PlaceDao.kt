package com.app.presentation.database

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
}
