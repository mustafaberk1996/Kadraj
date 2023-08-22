package com.example.kadraj.ui.user

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.kadraj.R
import com.example.kadraj.data.state.GetUserState
import com.example.kadraj.data.state.UpdateUserState
import com.example.kadraj.databinding.FragmentUserUpdateBinding
import kotlinx.coroutines.launch

class UserUpdateFragment : Fragment(R.layout.fragment_user_update) {

    private lateinit var binding: FragmentUserUpdateBinding
    private val viewModel: UserUpdateViewModel by activityViewModels()
    private val args:UserUpdateFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUserUpdateBinding.bind(view)

        viewModel.getUser(args.userID)
        observeGetUserState()
        observeUpdateUser()

        binding.btnUpdate.setOnClickListener {
            viewModel.updateUser(
                binding.etUserName.text.toString().trim(),
                binding.etUserSurname.text.toString().trim(),
                binding.etUserEmail.text.toString().trim(),
                binding.etUserPassword.text.toString().trim(),
                args.userID)
        }
    }

    private fun observeGetUserState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.getUserState.collect {
                    when (it) {
                        GetUserState.Idle->{}
                        is GetUserState.Result->{
                            binding.etUserName.setText(it.user.name)
                            binding.etUserSurname.setText(it.user.surname)
                            binding.etUserEmail.setText(it.user.email)
                            binding.etUserPassword.setText(it.user.password)
                        }
                        is GetUserState.Error->{}
                    }
                }
            }
        }
    }

    private fun observeUpdateUser() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.updateUserState.collect {
                    when (it) {
                        UpdateUserState.Idle->{}
                        UpdateUserState.Loading->{}
                        is UpdateUserState.Success->{
                            Toast.makeText(requireContext(),"User Updated", Toast.LENGTH_LONG)
                        }
                        is UpdateUserState.Error->{}
                    }
                }
            }
        }
    }
}