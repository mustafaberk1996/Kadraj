package com.example.kadraj.ui.photoList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.dao.PhotoFavouriteDao
import com.example.kadraj.data.entity.PhotoFavourite
import com.example.kadraj.data.repository.PhotoRepository
import com.example.kadraj.data.state.PhotoFavouriteAdapterState
import com.example.kadraj.data.state.PhotoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photoRepository: PhotoRepository,
    private val photoFavouriteDao: PhotoFavouriteDao
):ViewModel() {

    private val _photoListState: MutableStateFlow<PhotoListState> = MutableStateFlow(PhotoListState.Idle)
    val photoListState: StateFlow<PhotoListState> = _photoListState

    private val _photoFavouriteAdapterState: MutableStateFlow<PhotoFavouriteAdapterState> = MutableStateFlow(PhotoFavouriteAdapterState.Idle)
    val photoFavouriteAdapterState: StateFlow<PhotoFavouriteAdapterState> = _photoFavouriteAdapterState

    fun getAllPhotos(){
        viewModelScope.launch {
            runCatching {
                _photoListState.value = PhotoListState.Loading
                val photos = photoRepository.getAllPhotos()
                if (photos.isEmpty()) _photoListState.value = PhotoListState.Empty
                else _photoListState.value = PhotoListState.Result(photos)
            }.onFailure {
                _photoListState.value = PhotoListState.Error(it)
            }
        }
    }

    fun insertOrDeleteFavouritePhoto(photo:Photo,position:Int){
        viewModelScope.launch {
            kotlin.runCatching {
                if (photo.isFavourite){
                    photo.isFavourite=false
                    val photoFavouriteItem = photoRepository.getFavouritePhotoByPhotoId(photo.id)
                    if (photoFavouriteItem != null) {
                        photoRepository.delete(photoFavouriteItem)
                        println("${photoFavouriteItem.id} silindi")
                    }
                    _photoFavouriteAdapterState.value = PhotoFavouriteAdapterState.Changed(position)
                    _photoFavouriteAdapterState.value = PhotoFavouriteAdapterState.Idle
                }else{
                    photo.isFavourite=true
                    val photoFavouriteNew = PhotoFavourite(photoId = photo.id, photoUrl = photo.url)
                     photoRepository.insert(photoFavouriteNew)
                    println("${photoFavouriteNew.photoId} eklendi")
                    photoRepository.getFavouritePhotos().forEach {
                        println(it.id)
                    }
                    _photoFavouriteAdapterState.value = PhotoFavouriteAdapterState.Changed(position)
                    _photoFavouriteAdapterState.value = PhotoFavouriteAdapterState.Idle

                }
            }
        }
    }


}