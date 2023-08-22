package com.example.kadraj.data.state

import com.example.kadraj.data.entity.User

sealed class GetUserState {
    object Idle : GetUserState()
    class Result(val user: User) : GetUserState()
    class Error(val throwable: Throwable) : GetUserState()
}
