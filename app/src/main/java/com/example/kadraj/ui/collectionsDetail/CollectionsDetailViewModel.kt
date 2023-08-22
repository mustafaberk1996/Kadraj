package com.example.kadraj.ui.collectionsDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.repository.CollectionRepository
import com.example.kadraj.data.state.CollectionDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionsDetailViewModel @Inject constructor(private val collectionRepository: CollectionRepository) : ViewModel() {

    private val _collectionDetailState: MutableStateFlow<CollectionDetailState> = MutableStateFlow(CollectionDetailState.Idle)
    val collectionDetailState: StateFlow<CollectionDetailState> = _collectionDetailState

    fun getCollectionById(id: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _collectionDetailState.value = CollectionDetailState.Loading
                val collection = collectionRepository.getCollectionById(id)
                if (collection != null) {
                } else {
                    _collectionDetailState.value = CollectionDetailState.Empty
                }
            }.onFailure {
                _collectionDetailState.value = CollectionDetailState.Error()
            }
        }

    }

}