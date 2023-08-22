package com.example.kadraj.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kadraj.Constants.LOGGED_USER_ID
import com.example.kadraj.Constants.SHARED_PREF_NAME
import com.example.kadraj.R
import com.example.kadraj.data.state.RegisterMessageState
import com.example.kadraj.data.state.UserAddState
import com.example.kadraj.databinding.FragmentRegisterBinding
import kotlinx.coroutines.launch
import com.example.kadraj.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {


    private val viewModel: RegisterViewModel by activityViewModels()
    lateinit var binding: FragmentRegisterBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)


        observeUserAddState()
        observeMessage()
        listeners()

    }

    private fun observeMessage() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.message.collect{
                    when(it){
                        RegisterMessageState.Idle ->{}
                        RegisterMessageState.UserAlreadyExists->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.user_already_exists).create().show()
                        }
                        RegisterMessageState.Success ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.login_success).create().show()
                        }
                        RegisterMessageState.Empty ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.fill_in_the_blank).create().show()
                        }
                        RegisterMessageState.PasswordsNotEquals ->{
                            AlertDialog.Builder(requireContext()).setMessage(R.string.passwords_not_equals).create().show()
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
                            binding.progressBarRegister.isVisible = true
                        }
                        is UserAddState.Success ->{
                            binding.progressBarRegister.isVisible = false
                            val preferences = activity?.getSharedPreferences(SHARED_PREF_NAME, AppCompatActivity.MODE_PRIVATE)
                            preferences?.edit()?.putInt(LOGGED_USER_ID,it.userId)?.apply()
                            findNavController().navigate(R.id.action_registerFragment_to_mainFragment)
                        }
                        is UserAddState.Error ->{
                            binding.progressBarRegister.isVisible = false
                            AlertDialog.Builder(requireContext()).setTitle(R.string.somethings_wrong).setMessage(it.throwable.message)
                        }
                    }
                }
            }
        }
    }

    private fun listeners() {
        binding.btnSignUpRegister.setOnClickListener {
            viewModel.insert(binding.etName.text.toString().trim(),binding.etSurname.text.toString().trim(),binding.etEmail.text.toString().trim(), binding.etPassword.text.toString().trim(), binding.etConfirmPassword.text.toString().trim())
        }

        binding.btnLoginRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }
}