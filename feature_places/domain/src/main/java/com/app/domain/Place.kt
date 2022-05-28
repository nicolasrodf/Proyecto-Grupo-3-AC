package com.app.domain

data class Place(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val largeDescription: String,
    val images: List<String>,
    val location: String,
    val latitude: String,
    val longitude: String,
    val favorite: Boolean
)
