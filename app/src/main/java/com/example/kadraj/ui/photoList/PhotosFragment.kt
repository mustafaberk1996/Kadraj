package com.example.kadraj.ui.photoList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kadraj.R
import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.data.state.PhotoListState
import com.example.kadraj.databinding.FragmentPhotosBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch


class PhotosFragment : Fragment(R.layout.fragment_photos) {

    private val viewModel: PhotosViewModel by activityViewModels()
    private lateinit var binding: FragmentPhotosBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPhotosBinding.bind(view)



        viewModel.getAllPhotos()

        observePhotoListState()

    }

    private fun onClick(photo: Photo){


    }

private fun observePhotoListState() {
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.CREATED){
            viewModel.photoListState.collect{
                when(it){
                    is PhotoListState.Idle->{}
                    is PhotoListState.Loading->{
                        binding.rvPhotos.isVisible = false
                        binding.progressBar.isVisible = true
                    }
                    is PhotoListState.Empty->{
                        binding.rvPhotos.isVisible = false
                        binding.progressBar.isVisible = false
                    }
                    is PhotoListState.Result->{
                        binding.rvPhotos.isVisible = true
                        binding.progressBar.isVisible = false
                        binding.rvPhotos.adapter = PhotoAdapter(requireContext(),it.photos,this@PhotosFragment::onClick)
                    }
                    is PhotoListState.Error->{
                        binding.rvPhotos.isVisible = false
                        binding.progressBar.isVisible = false
                        Snackbar.make(binding.rvPhotos,"Bir hata olustu", Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}
}

