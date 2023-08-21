package com.example.kadraj.data.state

sealed class VideoListState {
    object Idle:VideoListState ()
    object Loading: VideoListState ()
    object Success: VideoListState ()
    class Error(val throwable: Throwable): VideoListState ()
}