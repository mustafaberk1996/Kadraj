package com.example.kadraj.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.example.kadraj.AppDatabase
import com.example.kadraj.R
import com.example.kadraj.data.state.LoginMessageState
import com.example.kadraj.data.state.LoginState
import com.example.kadraj.data.state.UserAddState
import com.example.kadraj.databinding.FragmentLoginBinding
import com.example.kadraj.ui.MainFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel:LoginViewModel by activityViewModels()
    lateinit var binding:FragmentLoginBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        observeUserAddState()
        observeMessage()
        listeners()

        observeLogin()

        binding.btnLogin.setOnClickListener {
//            viewModel.login(AppDatabase.invoke(requireContext()) ,binding.etUser.text.toString(), binding.etPassword.text.toString())
        }


        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkMailAndPassword(binding.etEmail.text.toString(), binding.etPassword.text.toString())
            }

        })

        binding.etPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkMailAndPassword(binding.etPassword.text.toString(), binding.etPassword.text.toString())
            }

        })

    }

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


    private fun observeLogin() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.loginState.collect {
                    when (it) {
                        is LoginState.Idle -> {}
                        is LoginState.Loading -> {}
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

    private fun observeMessage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.message.collect{
                    when(it){
                        LoginMessageState.Idle ->{}
                        LoginMessageState.UserAlreadyExists->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.user_already_exists).create().show()
                        }
                        LoginMessageState.Success ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.login_success).create().show()
                        }
                        LoginMessageState.Empty ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.fill_in_the_blank).create().show()
                        }
                        LoginMessageState.UserNotFound ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.user_not_found).create().show()
                        }
                        LoginMessageState.InformationWrong ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.user_information_wrong).create().show()
                        }
                    }
                }
            }
        }
    }

    private fun observeUserAddState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.userAddState.collect{
                    when(it){
                        UserAddState.Idle ->{}
                        UserAddState.Loading ->{
                            binding.progressBarLogin.isVisible = true
                        }
                        is UserAddState.Success ->{
                            binding.progressBarLogin.isVisible = false
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                        is UserAddState.Error ->{
                            binding.progressBarLogin.isVisible = false
                            AlertDialog.Builder(requireContext()).setTitle(R.string.somethings_wrong).setMessage(it.throwable.message)
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.btnSignUp.setOnClickListener {
//            viewModel.insert(AppDatabase.invoke(requireContext()),binding.etUser.text.toString(), binding.etPassword.text.toString())
        }
    }
}