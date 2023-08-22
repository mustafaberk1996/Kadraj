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
import com.example.kadraj.adapter.CollectionDetailAdapter
import com.example.kadraj.data.state.CollectionDetailState
import com.example.kadraj.databinding.FragmentCollectionsDetailBinding
import com.example.kadraj.ui.collectionsDetail.CollectionsDetailViewModel
import kotlinx.coroutines.launch


class CollectionsDetailFragment : Fragment(R.layout.fragment_collections_detail) {
    lateinit var binding: FragmentCollectionsDetailBinding
    private val viewModel: CollectionsDetailViewModel by activityViewModels()
    lateinit var adapter: CollectionDetailAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentCollectionsDetailBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)



        //TODO Busra
        viewModel.getCollectionById("aadaw2")
        observeCollectionDetailState()
    }

    private fun observeCollectionDetailState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.collectionDetailState.collect {
                    when(it) {
                        CollectionDetailState.Idle -> {}
                        CollectionDetailState.Loading -> {
                            binding.rvCollections.isVisible = false
                            binding.progressBar.isVisible = true
                        }
                        CollectionDetailState.Empty -> {
                            binding.rvCollections.isVisible = false
                            binding.progressBar.isVisible = false
                            AlertDialog.Builder(requireContext()).setMessage("BURADA HİÇ BİR ŞEY YOK").create().show()
                        }
                        is CollectionDetailState.Result -> {
                            // adapter = CollectionDetailAdapter(requireContext(), it.collectionId)
                            binding.rvCollections.adapter = adapter
                            binding.rvCollections.isVisible = true
                            binding.progressBar.isVisible = false


                        }
                        is CollectionDetailState.Error -> {
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