package com.example.assessmentwork

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    // as we are making get request
    // so we are displaying
    // GET as annotation.
    // and inside we are passing last parameter for our url.
    @GET("0482585b-06a0-45a7-b14c-931067d084c7")
    fun getAllCourses(): Call<ArrayList<Facts>?>?
}