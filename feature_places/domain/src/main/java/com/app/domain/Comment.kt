package com.app.domain

data class Comment(
    val id: String,
    val idPlace: Int,
    val idUser: Int,
    val timeRegister: String,
    val nameUser: String,
    val commentText: String
)