package com.example.kadraj.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.AppDatabase
import com.example.kadraj.data.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _loginState:MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Idle)
    val loginState:StateFlow<LoginState> = _loginState


    fun login(database: AppDatabase, etMail: String, etPassword: String) {
        viewModelScope.launch {
            runCatching {
                _loginState.value = LoginState.Loading
                database.userDao().getUser(etMail, etPassword)?.let {
                    _loginState.value = LoginState.Result(it)
                }?: kotlin.run {
                    _loginState.value = LoginState.UserNotFound
                }
            }.onFailure {
                _loginState.value = LoginState.Error(it)
            }
        }
    }


}