package com.example.kadraj.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.AppDatabase
import com.example.kadraj.data.entity.User
import com.example.kadraj.data.state.UserAddState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {

    private val _userAddState: MutableStateFlow<UserAddState> = MutableStateFlow(UserAddState.Idle)
    val userAddState: StateFlow<UserAddState> = _userAddState

    private fun insert(database: AppDatabase, name:String, surname:String, age:String, email:String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _userAddState.value = UserAddState.Loading
                val user = User(
                    email = email,
                    password = password
                )
                database.userDao().insert(user)
            }.onSuccess {
                _userAddState.value = UserAddState.Success
            }.onFailure {
                _userAddState.value = UserAddState.Error(it)
            }
        }
    }


}