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
import androidx.navigation.fragment.findNavController
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
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCollectionsBinding.bind(view)

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
                            //TODO Busra
                            adapter = CollectionsAdapter(requireContext(), it.collections) {
                                //val action = CollectionsFragmentDirections.actionCollectionsFragmentToCollectionsDetailFragment(it.id)
                                //findNavController().navigate(R.action.)

                            }
                            binding.rvCollections.adapter = adapter
                            binding.rvCollections.isVisible = true
                            binding.progressBar.isVisible = false


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