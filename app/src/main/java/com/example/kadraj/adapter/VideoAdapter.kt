package com.example.kadraj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.kadraj.data.api.model.Video
import com.example.kadraj.databinding.VideosListItemBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player.REPEAT_MODE_ONE
import kotlin.time.Duration.Companion.seconds


class VideoAdapter(val context: Context, val videoList:List<Video>,
                   val onClick: (video: Video) -> Unit,
                  /* val onVideoPlay:(video:Video)->Unit*/)
    :RecyclerView.Adapter<VideoAdapter.CustomViewHolder>() {
    private var currentPlayingVideo: Video? = null
    class CustomViewHolder(binding: VideosListItemBinding) : ViewHolder(binding.root) {
        //val ivVideoImage = binding.ivVideoImage
        val tvVideoPublisher = binding.tvVideoPublisher
        val tvVideoDuration = binding.tvVideoDuration
        var videoPlayer = binding.exoPlayer
        val thumbnail = binding.thumbnail


    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val binding = VideosListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video = videoList[position]
        val videoLink=video.video_files[0].link
        println(video.url)


                holder.tvVideoDuration.text= video.duration.seconds.toString()


        val player = ExoPlayer.Builder(context).build()
        holder.videoPlayer.player = player

        holder.videoPlayer.requestFocus()
        val mediaItem = MediaItem.fromUri(videoLink)
        player.setMediaItem(mediaItem, true)
        holder.thumbnail.load(video.image)

        holder.itemView.setOnClickListener {

            holder.videoPlayer.requestFocus()
            holder.thumbnail.visibility = View.GONE
            player.prepare()
            player.volume= 0F
            player.repeatMode = REPEAT_MODE_ONE
            player.play()
        }

}
}







