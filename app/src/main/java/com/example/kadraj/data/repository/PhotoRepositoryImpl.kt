package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.api.service.PhotoService
import com.example.kadraj.data.dao.PhotoDao
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(val photoService: PhotoService, private val photoDao: PhotoDao): PhotoRepository {
    override suspend fun getAllPhotos(): List<Photo> {
        return photoService.getAllPhotos("Animal").photos
    }

    override suspend fun getFavouritePhotos(): List<Photo>? {
        return photoDao.getFavouritePhotos()
    }
}