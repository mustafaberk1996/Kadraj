package com.example.kadraj

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import coil.load
import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.databinding.FragmentPhotoDetailBinding


class PhotoDetail : Fragment(R.layout.fragment_photo_detail) {

    private lateinit var binding:FragmentPhotoDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding=FragmentPhotoDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        val args=arguments
        val photoDetail=args?.getParcelable<Photo>("photoDetail")
        if (photoDetail!=null){
            binding.imgPhotoDetail.load(photoDetail.src.portrait)
            binding.txtPhotographer.text=photoDetail.photographer
        }
    }
}