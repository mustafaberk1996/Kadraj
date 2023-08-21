package com.example.kadraj.data.repository



import com.example.kadraj.data.api.model.Video
import com.example.kadraj.data.api.service.VideoService
import javax.inject.Inject

class VideoRepositoryImpl @Inject constructor(val video: VideoService):VideoRepository {
    override suspend fun getAllVideo(): List<Video> {
        return video.getAllVideo("car").videos
    }

}