package com.example.kadraj.data.state

sealed class LoginMessageState{
    object Idle:LoginMessageState()
    object Empty:LoginMessageState()
    object UserNotFound:LoginMessageState()
    object InformationWrong:LoginMessageState()
    object UserAlreadyExists:LoginMessageState()
    object Success:LoginMessageState()
}
