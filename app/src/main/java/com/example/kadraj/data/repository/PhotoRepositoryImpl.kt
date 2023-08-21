package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.api.service.PhotoService
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(val photoService: PhotoService): PhotoRepository {
    override suspend fun getAllPhotos(): List<Photo> {
        return photoService.getAllPhotos("Animal").photos
    }
}