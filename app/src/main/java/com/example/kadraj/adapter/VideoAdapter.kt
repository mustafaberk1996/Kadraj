package com.example.kadraj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutParams
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.kadraj.data.api.model.Video
import com.example.kadraj.databinding.VideosListItemBinding


class VideoAdapter(val context: Context, val videoList:List<Video>, val onClick: (video: Video) -> Unit):RecyclerView.Adapter<VideoAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding = VideosListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        binding.root.layoutParams = LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = videoList[position]

//        holder.tvVideoDuration.text
//        holder.tvVideoPublisher.text
        holder.ivVideoImage.load(video.image)

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