package com.example.kadraj.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.kadraj.data.api.model.Photo

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo WHERE isFavourite = :isFavourite")
    suspend fun getFavouritePhotos(isFavourite: Boolean = true): List<Photo>?
}