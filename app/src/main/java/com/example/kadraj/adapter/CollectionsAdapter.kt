package com.example.kadraj.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.kadraj.data.api.model.Collection
import com.example.kadraj.data.api.model.CollectionResponse
import com.example.kadraj.databinding.CollectionsListItemBinding

class CollectionsAdapter(
    val context: Context, val collections: List<Collection>
    // val onClick:(collection:Collection) -> Unit
):
    RecyclerView.Adapter<CollectionsAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(
            CollectionsListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return collections.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val collection = collections[position]

        // holder.ivCollection.load(collection.collections)

    }

    class CustomViewHolder(binding: CollectionsListItemBinding): RecyclerView.ViewHolder(binding.root) {

        val ivCollection = binding.ivCollections

    }
}