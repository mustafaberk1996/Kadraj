package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Collection

interface CollectionRepository {


    suspend fun getCollections():List<Collection>


}