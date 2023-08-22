package com.example.kadraj.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.AppDatabase
import com.example.kadraj.data.state.LoginMessageState
import com.example.kadraj.data.state.LoginState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _loginState:MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState:StateFlow<LoginState> = _loginState
    private val _message: MutableSharedFlow<LoginMessageState> = MutableSharedFlow()
    val message: SharedFlow<LoginMessageState> = _message


    fun login(database: AppDatabase, email: String, password: String) {
        viewModelScope.launch {
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                runCatching {
                    _loginState.value = LoginState.Loading
                    database.userDao().getUser(email, password)?.let {
                        _loginState.value = LoginState.Result(it)
                        _message.emit(LoginMessageState.Success)
                    } ?: kotlin.run {
                        _message.emit(LoginMessageState.UserNotFound)
                    }
                }.onFailure {
                    _loginState.value = LoginState.Error(it)
                }
            }else{
                _message.emit(LoginMessageState.Empty)
            }
        }
    }


}