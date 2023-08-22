package com.example.kadraj.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.room.RoomDatabase
import com.example.kadraj.AppDatabase
import com.example.kadraj.R
import com.example.kadraj.data.state.LoginMessageState
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
                            binding.progressBar.isVisible = true
                        }
                        UserAddState.Success ->{
                            binding.progressBar.isVisible = false
                            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                        }
                        is UserAddState.Error ->{
                            binding.progressBar.isVisible = false
                            AlertDialog.Builder(requireContext()).setTitle(R.string.somethings_wrong).setMessage(it.throwable.message)
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.btnSignUp.setOnClickListener {
            viewModel.insert(AppDatabase.invoke(requireContext()),binding.etUser.text.toString(), binding.etPassword.text.toString())
        }
    }
}