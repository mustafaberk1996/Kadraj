package com.example.kadraj.ui.collections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.repository.CollectionRepository
import com.example.kadraj.data.state.CollectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor(private val collectionRepository: CollectionRepository):ViewModel(){

    private val _collectionState : MutableStateFlow<CollectionState> = MutableStateFlow(CollectionState.Idle)
    val collectionState : StateFlow<CollectionState> = _collectionState


    fun getCollections() {
        viewModelScope.launch {
            kotlin.runCatching {
                _collectionState.value = CollectionState.Loading
                val collections = collectionRepository.getCollections().collections
                if(collections.isNotEmpty()) {
                    _collectionState.value = CollectionState.Result(collections)
                }else{
                    _collectionState.value = CollectionState.Empty
                }
            }.onFailure {
                _collectionState.value = CollectionState.Error()
            }
        }
    }
}