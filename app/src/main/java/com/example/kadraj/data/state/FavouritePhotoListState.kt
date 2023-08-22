package com.example.kadraj.data.state

import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.entity.PhotoFavourite

sealed class FavouritePhotoListState {
    object Idle: FavouritePhotoListState()
    object Loading: FavouritePhotoListState()
    class Result(val photos: List<PhotoFavourite>): FavouritePhotoListState()
    class Error(val throwable: Throwable): FavouritePhotoListState()
}