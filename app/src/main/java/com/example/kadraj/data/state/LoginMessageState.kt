package com.example.kadraj.data.state

sealed class LoginMessageState{
    object Idle:LoginMessageState()
    object Success:LoginMessageState()
    object Empty:LoginMessageState()
    object UserNotFound:LoginMessageState()
    object InformationWrong:LoginMessageState()
    object UserAlreadyExists:LoginMessageState()
}
