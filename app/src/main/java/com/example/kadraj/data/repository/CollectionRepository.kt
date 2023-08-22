package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.collection.Collection
import com.example.kadraj.data.api.model.collection.CollectionContentResponse
import com.example.kadraj.data.api.model.collection.CollectionResponse

interface CollectionRepository {


    suspend fun getCollectionById(id:String): CollectionContentResponse

    suspend fun getCollections() : CollectionResponse


}