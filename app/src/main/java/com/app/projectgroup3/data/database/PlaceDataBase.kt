package com.app.projectgroup3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Place::class], version = 1, exportSchema = false)
abstract class PlaceDataBase : RoomDatabase() {
    abstract fun placeDao():PlaceDao


}