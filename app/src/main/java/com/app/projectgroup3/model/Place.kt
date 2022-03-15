package com.app.projectgroup3.model

data class Place (
    val id: String,
    val name: String,
    val shortDescription: String,
    val largeDescription: String,
    val image : String,
    val location: String,
    val latitude: Double,
    val longitude: Double
    )