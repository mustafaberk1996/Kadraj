package com.example.kadraj.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.state.VideoListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class VideoViewModel @Inject constructor(
    //private val videoRepository: VideoRepository
): ViewModel() {

    private val _videoListState: MutableStateFlow<VideoListState> = MutableStateFlow(VideoListState.Idle)
    val videoListState: StateFlow<VideoListState> = _videoListState

    fun getAllVideos(){
        viewModelScope.launch {
            runCatching {
                _videoListState.value = VideoListState.Loading
                //val videos = videoRepository.getAllVideos()
                //if (videos.isEmpty()) _videoListState.value = VideoListState.Empty
               // else _videoListState.value = VideoListState.Result(videos)
            }.onFailure {
                _videoListState.value = VideoListState.Error(it)
            }
        }
    }

}