package com.example.kadraj.data.state

sealed class UserAddState {
    object Idle:UserAddState()
    object Loading: UserAddState()
    object Success: UserAddState()
    class Error(val throwable: Throwable): UserAddState()
}