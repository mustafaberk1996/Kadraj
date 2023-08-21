package com.example.kadraj.ui.collections

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.kadraj.R
import com.example.kadraj.adapter.CollectionsAdapter
import com.example.kadraj.data.state.CollectionState
import com.example.kadraj.databinding.FragmentCollectionsBinding
import kotlinx.coroutines.launch


class CollectionsFragment : Fragment(R.layout.fragment_collections) {
    lateinit var binding: FragmentCollectionsBinding
    private val viewModel:CollectionsViewModel by activityViewModels()
    lateinit var adapter: CollectionsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCollectionsBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCollections()
        observeCollectionState()
    }

    private fun observeCollectionState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.collectionState.collect {
                    when(it) {
                        CollectionState.Idle -> {}
                        CollectionState.Loading -> {
                            binding.rvCollections.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        CollectionState.Empty -> {
                            binding.rvCollections.isVisible = false
                            binding.progressBar.isVisible = false
                            AlertDialog.Builder(requireContext()).setMessage("BURADA HİÇ BİR ŞEY YOK").create().show()
                        }
                        is CollectionState.Result -> {
                            binding.rvCollections.isVisible = true
                            binding.progressBar.isVisible = false
                            adapter = CollectionsAdapter(requireContext(), it.collections)
                            binding.rvCollections.adapter = adapter
                            println(it.collections)

                        }
                        is CollectionState.Error -> {
                            binding.rvCollections.isVisible = false
                            binding.progressBar.isVisible = false
                            AlertDialog.Builder(requireContext()).setMessage("BİR SORUN OLUŞTU").create().show()

                        }

                    }
                }
            }
        }
    }

}