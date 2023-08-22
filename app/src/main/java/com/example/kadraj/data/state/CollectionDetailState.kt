package com.example.kadraj.data.state


sealed class CollectionDetailState {
    object Idle : CollectionDetailState()
    object Loading : CollectionDetailState()
    object Empty : CollectionDetailState()
    class Result(val collectionId: String) : CollectionDetailState ()
    class Error(val throwable: Throwable? = null) : CollectionDetailState()
}