package com.example.kadraj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.kadraj.databinding.VideosListItemBinding
import com.example.kadraj.model.Video

class VideoAdapter(val context:Context, val videoList:List<Video>):RecyclerView.Adapter<VideoAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            VideosListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = videoList[position]

        holder.tvVideoDuration.text
        holder.tvVideoPublisher.text
        holder.ivVideoImage.load(video.video_pictures)
    }

    class CustomViewHolder(binding:VideosListItemBinding):ViewHolder(binding.root) {
        val ivVideoImage = binding.ivVideoImage
        val tvVideoPublisher = binding.tvVideoPublisher
        val tvVideoDuration = binding.tvVideoDuration

    }
}