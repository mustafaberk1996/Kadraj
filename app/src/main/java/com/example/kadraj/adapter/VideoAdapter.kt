package com.example.kadraj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.kadraj.data.api.model.Video
import com.example.kadraj.databinding.VideosListItemBinding


class VideoAdapter(val context:Context, val videoList:List<Video>, val onClick:(video:Video) -> Unit):RecyclerView.Adapter<VideoAdapter.CustomViewHolder>() {

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

        holder.itemView.setOnClickListener {
            onClick(video)
        }
    }

    class CustomViewHolder(binding:VideosListItemBinding):ViewHolder(binding.root) {
        val ivVideoImage = binding.ivVideoImage
        val tvVideoPublisher = binding.tvVideoPublisher
        val tvVideoDuration = binding.tvVideoDuration

    }
}