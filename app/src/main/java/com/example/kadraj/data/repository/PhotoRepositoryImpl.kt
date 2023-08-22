package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.api.service.PhotoService
import com.example.kadraj.data.dao.PhotoFavouriteDao
import com.example.kadraj.data.entity.PhotoFavourite
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoService: PhotoService, private val photoFavouriteDao: PhotoFavouriteDao): PhotoRepository {
    override suspend fun getAllPhotos(): List<Photo> {
        val photos = photoService.getAllPhotos("Cat").photos
        photos.forEach {
            it.isFavourite = getFavouritePhotoByPhotoId(it.id) != null
            println("${it.id} is favorite => ${it.isFavourite}")
        }
        return photos
    }

    override suspend fun getFavouritePhotos(): List<PhotoFavourite> {
        return photoFavouriteDao.getFavouritePhotos()
    }

    override suspend fun insert(photoFavourite: PhotoFavourite) {
        return photoFavouriteDao.insert(photoFavourite)
    }

    override suspend fun delete(photoFavourite: PhotoFavourite) {
        return photoFavouriteDao.delete(photoFavourite)
    }

    override suspend fun getFavouritePhotoByPhotoId(id: Int): PhotoFavourite? {
        return photoFavouriteDao.getFavouritePhotoByPhotoId(id)
    }
}