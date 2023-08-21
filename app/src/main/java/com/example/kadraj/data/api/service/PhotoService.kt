package com.example.kadraj.data.api.service

import com.example.kadraj.Constants
import com.example.kadraj.data.api.model.Photo
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoService {

    @Headers(
        "Authorization: ${Constants.API_KEY}"
    )

    @GET("v1/search")
    suspend fun getAllPhotos(@Query("query") queryText:String): List<Photo>

}