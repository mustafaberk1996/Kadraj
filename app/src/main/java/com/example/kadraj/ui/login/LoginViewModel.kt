package com.example.kadraj.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.AppDatabase
import com.example.kadraj.data.entity.User
import com.example.kadraj.data.state.LoginMessageState
import com.example.kadraj.data.state.LoginState
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


    fun insert(database: AppDatabase, email:String, password:String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()){
//                val user = database.userDao().getUserByEmail(email)
//                user?.let {
//                    _message.emit(LoginMessageState.UserAlreadyExists)
//                }?: kotlin.run {
//                    kotlin.runCatching {
//                        _userAddState.emit(UserAddState.Loading)
//                        val user = User(
//                            email = email,
//                            password = password
//                        )
//                        database.userDao().insert(user)
//                    }.onSuccess {
//                        _message.emit(LoginMessageState.Success)
//                        _userAddState.value = UserAddState.Success
//                    }.onFailure {
//                        _userAddState.value = UserAddState.Error(it)
//                    }
//                }
//            }else{
//                _message.emit(LoginMessageState.Empty)
//            }
//        }
    }


}