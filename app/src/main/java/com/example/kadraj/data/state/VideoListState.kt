package com.example.kadraj.data.state

import com.example.kadraj.data.api.model.Video

sealed class VideoListState {
    object Idle:VideoListState ()
    object Loading: VideoListState ()
    object Empty: VideoListState ()
    class Error(val throwable: Throwable): VideoListState ()
    class Success(val videos : List<Video>): VideoListState ()
}