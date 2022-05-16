package com.app.presentation.di
/*
import android.app.Application
import androidx.room.Room
import com.app.data.datasource.PlaceLocalDataSource
import com.app.presentation.R
import com.app.presentation.database.PlaceDataBase
import com.app.presentation.database.PlaceRoomDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(app: Application): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        PlaceDataBase::class.java,
        "place_db"
    ).build()

    @Provides
    @Singleton
    fun providePlaceDao(db: PlaceDataBase) = db.placeDao()

}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
    @Binds
    abstract fun bindLocalDataSource(localDataSource: PlaceRoomDataSource):PlaceLocalDataSource

}*/
