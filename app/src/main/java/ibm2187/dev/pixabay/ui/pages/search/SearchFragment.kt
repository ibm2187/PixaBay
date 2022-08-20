package ibm2187.dev.pixabay.ui.pages.search

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ibm2187.dev.pixabay.common.base.ui.BaseFragment
import ibm2187.dev.pixabay.common.base.wrappers.ResponseWrapper
import ibm2187.dev.pixabay.common.utils.ext.getQueryChangeFlow
import ibm2187.dev.pixabay.common.utils.ext.init
import ibm2187.dev.pixabay.common.utils.ext.initGrid
import ibm2187.dev.pixabay.common.utils.ext.navigateWith
import ibm2187.dev.pixabay.databinding.FragmentSearchBinding
import ibm2187.dev.pixabay.model.network.responses.PixaBayResponse
import ibm2187.dev.pixabay.ui.MainActivityViewModel
import ibm2187.dev.pixabay.ui.adapters.PixaBayImagesAdapter
import ibm2187.dev.pixabay.ui.pages.discover.DiscoverFragmentDirections
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentSearchBinding
        get() = FragmentSearchBinding::inflate

    private val mainAdapter by lazy {
        PixaBayImagesAdapter {
            DiscoverFragmentDirections.dialogDetails(it).navigateWith(this@SearchFragment)
        }
    }

    private val vM by viewModels<SearchViewModel>()
    private val mainVM by activityViewModels<MainActivityViewModel>()
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
                    Log.d("SEARCH", "onViewCreated: query = ${query.first}")
                    //todo handle query empty
                    vM.setQuery(query.first?.toString())
                    vM.search()
                }
        }
        vM.searchObservable.observe(viewLifecycleOwner) {
            binding.progress.isVisible = it is ResponseWrapper.Loading
//            when (it) {
//                is ResponseWrapper.Failure -> onFailure()
//                is ResponseWrapper.LocalFailure -> onLocalFailure()
//                is ResponseWrapper.Success -> onData(it.value)
//            }
        }
    }

    override fun onResume() {
        super.onResume()
        mainVM.setToolbarLifted(true)
    }

    override fun onPause() {
        super.onPause()
        mainVM.setToolbarLifted(false)

    }
    private fun onLocalFailure() {

    }

    private fun onFailure() {

    }

    private fun onData(response: PixaBayResponse) {
        val hits = response.hits
        binding.noResults.isVisible = hits.isEmpty()
        binding.searchResults.isVisible = hits.isNotEmpty()
        if (hits.isNotEmpty()) {
            mainAdapter.setItems(hits)
        }
    }


}