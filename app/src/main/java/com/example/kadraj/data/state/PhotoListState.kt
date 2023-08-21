package com.example.kadraj.data.state

import com.example.kadraj.data.api.model.Photo


sealed class PhotoListState {
    object Idle : PhotoListState()
    object Loading : PhotoListState()
    object Empty : PhotoListState()
    class Result(val photos: List<Photo>) : PhotoListState()
    class Error(val throwable: Throwable? = null) : PhotoListState()
}