package com.example.kadraj.ui.videos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kadraj.R
import com.example.kadraj.databinding.FragmentVideosBinding
import kotlinx.coroutines.launch

class VideosFragment : Fragment(R.layout.fragment_videos) {
    private lateinit var binding: FragmentVideosBinding
    private val viewModel : VideoViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideosBinding.inflate(layoutInflater)

        //getVideos
        viewModel.getAllVideos()
        observeVideoListState()


    }

    private fun observeVideoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){

            }
        }

    }

}