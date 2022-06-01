package com.app.presentation.data.firebase

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class RemoteComment(
    var id: String="",
    var idPlace: Int=0,
    var idUser: Int,
    var timeRegister: String,
    var nameUser: String,
    var commentText: String
): Parcelable {
    constructor() : this(
        id = "ds",
        idPlace = 0,
        idUser = 0,
        timeRegister = "22-08-2022",
        nameUser = "J",
        commentText = "H"
    )
}