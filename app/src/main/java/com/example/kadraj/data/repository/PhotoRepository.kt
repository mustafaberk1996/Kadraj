package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Photo

interface PhotoRepository {

    suspend fun getAllPhotos():List<Photo>

}