package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Collection
import com.example.kadraj.data.api.service.CollectionService
import javax.inject.Inject


class CollectionRepositoryImpl @Inject constructor(val collectionService: CollectionService): CollectionRepository {


    override suspend fun getCollections(): List<Collection> {
        return collectionService.getCollections("per_page=1").collections
    }


}