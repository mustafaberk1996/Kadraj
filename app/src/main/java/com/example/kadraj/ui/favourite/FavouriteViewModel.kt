package com.example.kadraj.ui.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.repository.PhotoRepository
import com.example.kadraj.data.state.FavouritePhotoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val photoRepository: PhotoRepository) :
    ViewModel() {
    private val _favouritePhotoListState: MutableStateFlow<FavouritePhotoListState> =
        MutableStateFlow(FavouritePhotoListState.Idle)
    val favouritePhotoListState: StateFlow<FavouritePhotoListState> = _favouritePhotoListState

    fun getFavouritePhotos() {
        viewModelScope.launch {
            _favouritePhotoListState.emit(FavouritePhotoListState.Loading)

            photoRepository.getFavouritePhotos()?.let {
                _favouritePhotoListState.emit(FavouritePhotoListState.Result(it))
            } ?: kotlin.run {
                _favouritePhotoListState.emit(FavouritePhotoListState.Error(NullPointerException()))
            }
        }
    }
}