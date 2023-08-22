package com.example.kadraj.ui.userProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kadraj.data.repository.UserRepository
import com.example.kadraj.data.state.GetUserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserProfileViewModel @Inject constructor(
    private val userRepository: UserRepository
):ViewModel() {

//    private val _getUserState:MutableStateFlow<GetUserState> = MutableStateFlow(GetUserState.Idle)
//    val getUserState:StateFlow<GetUserState> =_getUserState
//
//
//    fun getUser(id:Int){
//        viewModelScope.launch {
//            runCatching {
//                val user=userRepository.getUserById(id)
//                _getUserState.value =GetUserState.Result(user!!)
//            }.onFailure {
//                _getUserState.value=GetUserState.Error(it)
//            }
//        }
//
//    }


}