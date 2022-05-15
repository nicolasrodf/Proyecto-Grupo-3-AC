package com.app.projectgroup3.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Place(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("shortDescription")
    val shortDescription: String,
    @SerializedName("largeDescription")
    val largeDescription: String,
    @SerializedName("images")
    val images: List<String>,
    @SerializedName("location")
    val location: String,
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
) : Parcelable