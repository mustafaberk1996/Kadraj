package com.example.kadraj.ui.videos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kadraj.R
import com.example.kadraj.adapter.VideoAdapter
import com.example.kadraj.data.state.VideoListState
import com.example.kadraj.databinding.FragmentVideosBinding

import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideosFragment : Fragment(R.layout.fragment_videos) {
    private lateinit var binding: FragmentVideosBinding
    private val viewModel: VideoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideosBinding.inflate(layoutInflater)

        observeVideoListState()

    }

    private fun observeVideoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.videoListState.collect{
                    when(it){
                        is VideoListState.Idle ->{}
                        is VideoListState.Loading ->{

                            binding.rvVideos.isVisible = false
                            binding.progressBarVideosPage.isVisible = true
                        }
                        is VideoListState.Success->{
                            binding.rvVideos.isVisible = true
                            binding.progressBarVideosPage.isVisible = false
                            //binding.rvVideos.adapter = VideoAdapter(this@VideosFragment,it)

                        }
                        is VideoListState.Error ->{
                            binding.rvVideos.isVisible = false
                            binding.progressBarVideosPage.isVisible = false
                            Snackbar.make(binding.rvVideos,"Bir hata olştu", Snackbar.LENGTH_LONG)

                        }
                        else -> {}
                    }
                }
            }

        }

    }

}