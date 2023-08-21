package com.example.kadraj.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.repository.PhotoRepository
import com.example.kadraj.data.state.PhotoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(private val photoRepository: PhotoRepository):ViewModel() {

    private val _photoListState:MutableStateFlow<PhotoListState> = MutableStateFlow(PhotoListState.Idle)
    val photoListState:StateFlow<PhotoListState> =_photoListState

    fun getAllPhotos(){
        viewModelScope.launch {
            kotlin.runCatching {
                _photoListState.value =PhotoListState.Loading
                delay(1000)
                val photos = photoRepository.getAllPhotos()
                if(photos.isEmpty()) _photoListState.value =PhotoListState.Empty
                else _photoListState.value =PhotoListState.Result(photos)
            }.onFailure {
                _photoListState.value =PhotoListState.Error(it)
            }
        }
    }
}