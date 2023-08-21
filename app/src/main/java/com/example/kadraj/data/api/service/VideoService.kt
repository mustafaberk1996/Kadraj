package com.example.kadraj.data.api.service

import com.example.kadraj.Constants
import com.example.kadraj.data.api.model.ResponseVideoList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideoService {

    @Headers(
        "Authorization:${Constants.API_KEY}"
    )

    @GET("videos/search")
    suspend fun getAll(@Query("query") queryText:String):ResponseVideoList


}