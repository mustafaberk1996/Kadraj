package com.example.kadraj.data.api.model

import androidx.room.Entity

@Entity
data class Photo(
    val alt: String,
    val avg_color: String,
    val height: Int,
    val id: Int,
    val liked: Boolean,
    val photographer: String,
    val photographer_id: Int,
    val photographer_url: String,
    val src: Src,
    val url: String,
    val width: Int,
    val isFavourite: Boolean = false
)