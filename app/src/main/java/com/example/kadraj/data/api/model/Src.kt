package com.example.kadraj.data.api.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Src(
    @PrimaryKey val srcId: Int = 0,
    val landscape: String,
    val large: String,
    val large2x: String,
    val medium: String,
    val original: String,
    val portrait: String,
    val small: String,
    val tiny: String
)