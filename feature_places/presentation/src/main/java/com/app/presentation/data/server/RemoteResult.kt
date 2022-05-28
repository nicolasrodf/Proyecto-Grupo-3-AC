package com.app.presentation.data.server

import com.google.gson.annotations.SerializedName

data class RemoteResult(
    val page: Int,
    val results: List<RemotePlace>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)

data class RemotePlace(
    val id: Int,
    val name: String,
    val shortDescription: String,
    val largeDescription: String,
    val location: String,
    val latitude: String,
    val longitude: String
)