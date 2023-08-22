package com.example.kadraj.ui.photoList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.kadraj.PhotoDetail
import com.example.kadraj.R
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
                        binding.rvPhotos.adapter = PhotoAdapter(requireContext(),it.photos){photo->
                            val bundle= bundleOf()
                            bundle.putParcelable("photoDetail",photo)
                            val photoDetail=PhotoDetail()
                            photoDetail.arguments=bundle
                            findNavController().navigate(R.id.action_photosFragment_to_photoDetail,bundle)
                        }
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

