package com.app.projectgroup3.data.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Place(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val shortDescription: String,
    val largeDescription: String,
    val images: String,
    val location: String,
    val latitude: String,
    val longitude: String,
    val popularity: Double
): Parcelable