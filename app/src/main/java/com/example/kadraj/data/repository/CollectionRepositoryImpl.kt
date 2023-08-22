package com.example.kadraj.data.repository
import com.example.kadraj.data.api.model.collection.Collection
import com.example.kadraj.data.api.model.collection.CollectionContentResponse
import com.example.kadraj.data.api.model.collection.CollectionResponse
import com.example.kadraj.data.api.service.CollectionService
import javax.inject.Inject


class CollectionRepositoryImpl @Inject constructor(val collectionService: CollectionService): CollectionRepository {


    override suspend fun getCollectionById(id: String): CollectionContentResponse {
        return collectionService.getCollectionById("9mp14cx")
    }

    override suspend fun getCollections(): CollectionResponse {
        return collectionService.getCollections()
    }


}