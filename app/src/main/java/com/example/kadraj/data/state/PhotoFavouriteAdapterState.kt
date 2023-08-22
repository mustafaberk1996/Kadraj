package com.example.kadraj.data.state

sealed class PhotoFavouriteAdapterState{
    object Idle:PhotoFavouriteAdapterState()
    class Changed(val position:Int):PhotoFavouriteAdapterState()
}
