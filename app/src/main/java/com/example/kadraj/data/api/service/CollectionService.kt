package com.example.kadraj.data.api.service

import com.example.kadraj.Constants
import com.example.kadraj.data.api.model.collection.Collection
import com.example.kadraj.data.api.model.collection.CollectionContentResponse
import com.example.kadraj.data.api.model.collection.CollectionResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CollectionService {

    @Headers("Authorization: ${Constants.API_KEY}")
    @GET("/v1/{id}/collections")
    suspend fun getCollectionById(@Path("id") id:String): CollectionContentResponse

    @Headers("Authorization: ${Constants.API_KEY}")
    @GET("/v1/collections")
    suspend fun getCollections() : CollectionResponse

}