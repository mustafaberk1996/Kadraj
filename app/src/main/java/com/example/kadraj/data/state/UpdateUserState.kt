package com.example.kadraj.data.state

import com.example.kadraj.data.entity.User

sealed class UpdateUserState {

    object Idle:UpdateUserState()
    object Loading:UpdateUserState()
    class Success(val user: User):UpdateUserState()
    class Error(val throwable: Throwable):UpdateUserState()
}