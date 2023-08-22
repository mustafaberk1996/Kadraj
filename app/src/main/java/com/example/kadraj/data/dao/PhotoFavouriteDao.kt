package com.example.kadraj.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.kadraj.data.entity.PhotoFavourite

@Dao
interface PhotoFavouriteDao {

    @Query("SELECT * FROM PhotoFavourite")
    suspend fun getFavouritePhotos(): List<PhotoFavourite>

    @Insert
    suspend fun insert(photoFavourite: PhotoFavourite)

    @Delete
    suspend fun delete(photoFavourite: PhotoFavourite)

    @Query("select * from PhotoFavourite where photoId = :id")
    suspend fun getFavouritePhotoByPhotoId(id: Int): PhotoFavourite?
}