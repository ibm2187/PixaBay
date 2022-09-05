package ibm2187.dev.pixabay.ui.pages.search

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.ibm2187.core.utils.ext.getQueryChangeFlow
import dev.ibm2187.core.utils.ext.initGrid
import dev.ibm2187.core.utils.ext.navigateWith
import ibm2187.dev.pixabay.common.base.ui.BaseFragment
import ibm2187.dev.pixabay.common.base.wrappers.ResponseWrapper
import ibm2187.dev.pixabay.databinding.FragmentSearchBinding
import dev.ibm2187.pixabay.core.data.network.responses.PixaBayResponse
import ibm2187.dev.pixabay.ui.adapters.PixaBayImagesAdapter
import ibm2187.dev.pixabay.ui.pages.discover.DiscoverFragmentDirections
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val mainAdapter = PixaBayImagesAdapter {
        DiscoverFragmentDirections.dialogDetails(it).navigateWith(this@SearchFragment)
    }


    private val vM by viewModels<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vM.search()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchResults.initGrid(mainAdapter, GridLayoutManager(requireContext(), 2))

        binding.retryBtn.setOnClickListener {
            vM.search()
        }
        lifecycleScope.launch {
            binding.searchView.getQueryChangeFlow()
                .debounce(800)
                .distinctUntilChanged()
                .asLiveData()
                .observe(viewLifecycleOwner) { query ->
                    vM.setQuery(query.first)
                    vM.search()
                }
        }
        vM.searchObservable.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it is ResponseWrapper.Loading
            when (it) {
                is ResponseWrapper.Failure -> onFailure()
                is ResponseWrapper.LocalFailure -> onLocalFailure()
                is ResponseWrapper.Success -> onData(it.value)
                ResponseWrapper.Loading -> onLoading()
            }
        }
    }

    private fun onLoading() {

    }


    private fun onLocalFailure() {

    }

    private fun onFailure() {

    }

    private fun onData(response: PixaBayResponse) {
        val hits = response.hits
        binding.noResults.isVisible = hits.isEmpty()
        binding.searchResults.isVisible = hits.isNotEmpty()
        mainAdapter.submitList(hits)
    }


}