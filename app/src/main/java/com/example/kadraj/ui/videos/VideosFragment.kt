package com.example.kadraj.ui.videos

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kadraj.R
import com.example.kadraj.adapter.VideoAdapter
import com.example.kadraj.data.api.model.Video
import com.example.kadraj.data.state.VideoListState
import com.example.kadraj.databinding.FragmentVideosBinding
import com.example.kadraj.databinding.VideosListItemBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.material.internal.ViewUtils.dpToPx

import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideosFragment : Fragment(R.layout.fragment_videos) {
    private lateinit var binding: FragmentVideosBinding
    private val viewModel: VideoViewModel by activityViewModels()
    lateinit var recyclerView: RecyclerView
    private var situation: Boolean = false
    lateinit var player: ExoPlayer


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentVideosBinding.bind(view)

        recyclerView = binding.rvVideos
        viewModel.getAll()
        observeVideoListState()
        recyclerView.onScrollStateChanged(1)

        //    situation= recyclerView.layoutManager?.isViewPartiallyVisible(requireView(),true,false) == true


    }


    private fun observeVideoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.videoListState.collect {
                    println(it)
                    when (it) {
                        is VideoListState.Idle -> {}
                        is VideoListState.Empty -> {}
                        is VideoListState.Loading -> {
                            binding.rvVideos.isVisible = false
                            binding.progressBarVideosPage.isVisible = true
                        }

                        is VideoListState.Success -> {
                            binding.rvVideos.isVisible = true
                            binding.progressBarVideosPage.isVisible = false
                            binding.rvVideos.adapter = VideoAdapter(
                                requireContext(),
                                it.videos,
                                this@VideosFragment::onClick/*,this@VideosFragment::onVideoPlay*/
                            )
                        }

                        is VideoListState.Error -> {
                            binding.rvVideos.isVisible = false
                            binding.progressBarVideosPage.isVisible = false
                            Snackbar.make(binding.rvVideos, "Bir hata olştu", Snackbar.LENGTH_LONG)
                                .show()

                        }
                    }
                }
            }
        }
    }

//    private fun onVideoPlay(video:Video) {
//
////        player = ExoPlayer.Builder(requireContext()).build()
////
////        val mediaItem = MediaItem.fromUri(video.url)
////        player.setMediaItem(mediaItem)
////        player.prepare()
////        player.play()
//    }

//    override fun onPause() {
//        super.onPause()
//
//        player.pause()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        player.release()
//    }


    private fun onClick(video: Video) {
    }


}


