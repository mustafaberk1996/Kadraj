package com.example.kadraj.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.AppDatabase
import com.example.kadraj.data.entity.User
import com.example.kadraj.data.state.LoginMessageState
import com.example.kadraj.data.state.UserAddState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _userAddState: MutableStateFlow<UserAddState> = MutableStateFlow(UserAddState.Idle)
    val userAddState: StateFlow<UserAddState> = _userAddState

    private val _message:MutableSharedFlow<LoginMessageState> = MutableSharedFlow()
    val message:SharedFlow<LoginMessageState> = _message




}