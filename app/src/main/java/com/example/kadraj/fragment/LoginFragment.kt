package com.example.kadraj.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.example.kadraj.AppDatabase
import com.example.kadraj.AppDatabase_Impl
import com.example.kadraj.R
import com.example.kadraj.data.state.LoginState
import com.example.kadraj.databinding.FragmentLoginBinding
import com.example.kadraj.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    @SuppressLint("ResourceAsColor")
    fun checkMailAndPassword(email:String, password:String) {
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            binding.btnLogin.isClickable = false
            val passiveColor = ContextCompat.getColor(requireContext(), R.color.loginpassive)
            binding.btnLogin.setBackgroundColor(passiveColor)
        } else {
            binding.btnLogin.isClickable = true
            val activeColor = ContextCompat.getColor(requireContext(), R.color.loginactive)
            binding.btnLogin.setBackgroundColor(activeColor)
        }
    }

    private lateinit var binding:FragmentLoginBinding
    private val viewModel:LoginViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        observeLogin()

        binding.btnLogin.setOnClickListener {
//            viewModel.login(AppDatabase.invoke(requireContext()) ,binding.etUser.text.toString(), binding.etPassword.text.toString())
        }


        binding.etUser.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkMailAndPassword(binding.etUser.text.toString(), binding.etPassword.text.toString())
            }

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkMailAndPassword(binding.etUser.text.toString(), binding.etPassword.text.toString())
            }

        })
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

    override fun onResume() {
        binding.btnLogin.isClickable = false
        super.onResume()
    }
}



























