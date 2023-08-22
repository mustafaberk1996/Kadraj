package com.example.kadraj.data.state

sealed class UserAddState {
    object Idle:UserAddState()
    object Loading: UserAddState()
    class Success(val userId:Int): UserAddState()
    class Error(val throwable: Throwable): UserAddState()
}