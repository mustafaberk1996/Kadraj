package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.entity.PhotoFavourite

interface PhotoRepository {

    suspend fun getAllPhotos():List<Photo>

    suspend fun getFavouritePhotos(): List<PhotoFavourite>

    suspend fun insert(photoFavourite: PhotoFavourite)

    suspend fun delete(photoFavourite: PhotoFavourite)
    suspend fun getFavouritePhotoByPhotoId(id: Int): PhotoFavourite?

}