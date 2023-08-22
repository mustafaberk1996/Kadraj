package com.example.kadraj.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.AppDatabase
import com.example.kadraj.data.entity.User
import com.example.kadraj.data.state.RegisterMessageState
import com.example.kadraj.data.state.UserAddState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel:ViewModel() {

    private val _userAddState: MutableStateFlow<UserAddState> = MutableStateFlow(UserAddState.Idle)
    val userAddState: StateFlow<UserAddState> = _userAddState

    private val _message: MutableSharedFlow<RegisterMessageState> = MutableSharedFlow()
    val message: SharedFlow<RegisterMessageState> = _message

    fun insert(database: AppDatabase,name:String, surname:String, email:String, password:String, confirm:String) {
        viewModelScope.launch(Dispatchers.IO) {
            var result:Long = -1
            if(!email.isNullOrEmpty() && !password.isNullOrEmpty()){
                if(password.equals(confirm)){
                    val user = User(name = name, surname = surname, email = email, password = password)

                    kotlin.runCatching {
                        _userAddState.emit(UserAddState.Loading)
                        result = database.userDao().insert(user)
                        if(result > -1){
                            _message.emit(RegisterMessageState.Success)
                        }else{
                            _message.emit(RegisterMessageState.UserAlreadyExists)
                        }
                    }.onSuccess {
                        _userAddState.value = UserAddState.Success(result.toInt())
                    }.onFailure {
                        _userAddState.value = UserAddState.Error(it)
                    }

                }else{
                    _message.emit(RegisterMessageState.PasswordsNotEquals)
                }
            }else{
                _message.emit(RegisterMessageState.Empty)
            }
        }
    }
}