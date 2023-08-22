package com.example.kadraj.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.entity.User
import com.example.kadraj.data.repository.UserRepository
import com.example.kadraj.data.state.GetUserState
import com.example.kadraj.data.state.UpdateUserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserUpdateViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _updateUserState: MutableStateFlow<UpdateUserState> = MutableStateFlow(UpdateUserState.Idle)
    val updateUserState: StateFlow<UpdateUserState> = _updateUserState

    private val _getUserState: MutableStateFlow<GetUserState> = MutableStateFlow(GetUserState.Idle)
    val getUserState: StateFlow<GetUserState> = _getUserState

    fun getUser(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                val user = userRepository.getUserById(id)
                _getUserState.value = GetUserState.Result(user!!)
            }.onFailure {
                _getUserState.value = GetUserState.Error(it)
            }
        }
    }

    fun updateUser(name:String,surname:String,email:String,password:String,id:Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                _updateUserState.value =UpdateUserState.Loading
                val user=User(name=name, surname = surname, email = email, password = password, id = id)
               userRepository.update(user)
                _updateUserState.value=UpdateUserState.Success(user)
            }.onFailure {
                _updateUserState.value=UpdateUserState.Error(it)
            }
        }
    }
}