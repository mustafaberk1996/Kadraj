package com.example.kadraj.data.state

import com.example.kadraj.data.entity.User

sealed class LoginState {

    object Idle:LoginState()
    object Loading:LoginState()
    class Result(val user: User):LoginState()
    object UserNotFound:LoginState()
    class Error(val throwable: Throwable):LoginState()

}