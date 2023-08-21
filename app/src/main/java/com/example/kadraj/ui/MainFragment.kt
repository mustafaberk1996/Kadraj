package com.example.kadraj.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.navigation.fragment.findNavController
import com.example.kadraj.R
import com.example.kadraj.databinding.FragmentMainBinding
import kotlin.system.exitProcess

class MainFragment : Fragment(R.layout.fragment_main) {
    lateinit var binding:FragmentMainBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.btnPhoto.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_photosFragment)
        }

        binding.btnVideo.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_videosFragment)
        }

        binding.btnUser.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_usersFragment)
        }
        binding.btnExit.setOnClickListener {
            activity?.finish()
        }

    }
}