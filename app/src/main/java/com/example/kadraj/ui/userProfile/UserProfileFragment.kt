package com.example.kadraj.ui.userProfile

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kadraj.App
import com.example.kadraj.Constants
import com.example.kadraj.Constants.LOGGED_USER_ID
import com.example.kadraj.Constants.SHARED_PREF_NAME
import com.example.kadraj.R
import com.example.kadraj.data.state.GetUserState
import com.example.kadraj.databinding.FragmentUserProfileBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class UserProfileFragment : Fragment(R.layout.fragment_user_profile) {


    private lateinit var binding: FragmentUserProfileBinding
    private val viewModel:UserProfileViewModel by activityViewModels ()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentUserProfileBinding.bind(view)


//        val sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,
//            AppCompatActivity.MODE_PRIVATE
//        )


        val sharedPreferences = activity?.getSharedPreferences(SHARED_PREF_NAME,AppCompatActivity.MODE_PRIVATE)

        val getId = sharedPreferences?.getInt(LOGGED_USER_ID,0)

        viewModel.getUser(getId!!)
        observeGetUserById()



        //verileri salihe göndereceksin
        binding.ivEdit.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_userUpdateFragment)
        }


    }

    private fun observeGetUserById() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.getUserState.collect{
                    when(it){
                        is GetUserState.Idle->{}
                        is GetUserState.Result->{
                            binding.tvFullName.text=""
                            binding.tvEmail.text=it.user.email
                            binding.tvPassword.text=it.user.password
                        }
                        is GetUserState.Error->{
                            Toast.makeText(requireContext(),R.string.user_not_found,Toast.LENGTH_LONG).show()
                        }

                    }
                }
            }
        }
    }

}