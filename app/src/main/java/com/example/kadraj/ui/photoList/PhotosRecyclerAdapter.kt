package com.example.kadraj.ui.photoList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kadraj.data.api.model.Photo
import com.example.kadraj.databinding.PhotoListItemBinding

class PhotoAdapter(private val context: Context, private val photos:List<Photo>, val onClick:(photo:Photo)->Unit):
    RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {


    class MyViewHolder(binding: PhotoListItemBinding): RecyclerView.ViewHolder(binding.root){

        val ivPhoto=binding.ivPhoto


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PhotoListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val photo=photos[position]
           holder.ivPhoto.load(photo.src.medium)

            holder.itemView.setOnClickListener {
              onClick(photo)
            }
        }
    }
