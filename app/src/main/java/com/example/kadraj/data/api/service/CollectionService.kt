package com.example.kadraj.data.api.service

import com.example.kadraj.Constants
import com.example.kadraj.data.api.model.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface CollectionService {

    @Headers(
        "Authorization: ${Constants.API_KEY}"
    )

    @GET("v1/collections")
    suspend fun getCollections(@Query("query") queryText:String): CollectionResponse

}