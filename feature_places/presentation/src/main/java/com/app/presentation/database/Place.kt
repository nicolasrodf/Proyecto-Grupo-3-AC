package com.app.presentation.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Place(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val shortDescription: String,
    val largeDescription: String,
    val image:String,
    val location: String,
    val latitude: String,
    val longitude: String,
    val favorite: Boolean
)