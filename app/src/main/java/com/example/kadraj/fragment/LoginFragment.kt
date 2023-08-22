package com.example.kadraj.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.example.kadraj.AppDatabase
import com.example.kadraj.R
import com.example.kadraj.data.state.LoginState
import com.example.kadraj.databinding.FragmentLoginBinding
import com.example.kadraj.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var binding:FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        observeLogin()


    }

    private fun observeLogin() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginState.collect {
                    when (it) {
                        is LoginState.Idle -> {}
                        is LoginState.Loading -> {}
                        is LoginState.UserNotFound -> {
                            // Elifin talimatnamesi ile burada user not found mesajı vereceğiz
                        }
                        is LoginState.Result -> {
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                        is LoginState.Error -> {
                            // Elifin talimatnamesi ile burada error mesajı vereceğiz
                        }
                    }
                }
            }
        }
    }
}



























