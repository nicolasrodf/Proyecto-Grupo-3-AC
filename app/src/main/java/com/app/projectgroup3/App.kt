package com.app.projectgroup3

import android.app.Application
import androidx.room.Room
import com.app.projectgroup3.data.database.PlaceDataBase

class App : Application() {
    lateinit var db: PlaceDataBase
        private set

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(
            this,
            PlaceDataBase::class.java, "place-db"
        ).build()
    }
}