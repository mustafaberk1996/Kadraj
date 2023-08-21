package com.example.kadraj.data.state

import com.example.kadraj.data.api.model.Photo

sealed class FavouritePhotoListState {
    object Idle: FavouritePhotoListState()
    object Loading: FavouritePhotoListState()
    class Result(val photos: List<Photo>): FavouritePhotoListState()
    class Error(val throwable: Throwable): FavouritePhotoListState()
}