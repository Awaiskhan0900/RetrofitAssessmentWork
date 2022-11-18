package com.example.assessmentwork

import com.google.gson.annotations.SerializedName

data class Facts(
    // on below line we are creating
    // two variable one for
    // course name and other for course image.
    @SerializedName("fact")
    var languageName: String,
    @SerializedName("length")
    var languageImg: Int
)