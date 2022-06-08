package com.app.testshared

import com.app.domain.Comment
import com.app.domain.Place

val samplePlace = Place(
    0,
    "title",
    "shortDescription",
    "largeDescription",
    ArrayList(),
    "location",
    "latitude",
    "longitude",
    false
)

val sampleComment = Comment(
    "id",
    1,
    0,
    "timeRegister",
    "nameUser",
    "commentText"
)