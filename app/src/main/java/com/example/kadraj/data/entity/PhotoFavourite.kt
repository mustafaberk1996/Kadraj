package com.example.kadraj.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoFavourite(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val photoId: Int?,
    val photoUrl: String?
    )