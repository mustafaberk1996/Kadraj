package com.example.kadraj.data.state

import com.example.kadraj.data.api.model.collection.Collection

sealed class CollectionState {
    object Idle : CollectionState()
    object Loading : CollectionState()
    object Empty : CollectionState()
    class Result(val collections: List<Collection>) : CollectionState()
    class Error(val throwable: Throwable? = null) : CollectionState()
}