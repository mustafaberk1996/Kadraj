package com.example.kadraj.data.repository

import com.example.kadraj.data.api.model.Video


interface VideoRepository {

   suspend fun getAllVideo(): List<Video>
}