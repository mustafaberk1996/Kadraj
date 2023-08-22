package com.example.kadraj.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kadraj.data.api.model.collection.Collection
import com.example.kadraj.data.api.model.collection.CollectionContentResponse
import com.example.kadraj.databinding.CollectionsDetailListItemBinding
import com.example.kadraj.databinding.CollectionsListItemBinding

class CollectionDetailAdapter(
    val context: Context, val collections: List<CollectionContentResponse>):
    RecyclerView.Adapter<CollectionDetailAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            CollectionsDetailListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val collection = collections[position]
        holder.ivCollection.load(collection.media)


    }

    class CustomViewHolder(binding: CollectionsDetailListItemBinding): RecyclerView.ViewHolder(binding.root) {

        val ivCollection = binding.ivCollections

    }
}