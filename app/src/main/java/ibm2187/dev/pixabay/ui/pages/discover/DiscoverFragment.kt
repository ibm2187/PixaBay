package ibm2187.dev.pixabay.ui.pages.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import ibm2187.dev.pixabay.common.base.ui.BaseFragment
import ibm2187.dev.pixabay.common.utils.ext.initGrid
import ibm2187.dev.pixabay.databinding.FragmentDiscoverBinding
import ibm2187.dev.pixabay.ui.adapters.PixaBayImagesAdapter

class DiscoverFragment : BaseFragment<FragmentDiscoverBinding>() {
    override val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentDiscoverBinding
        get() = FragmentDiscoverBinding::inflate


    private val mainAdapter by lazy {
        PixaBayImagesAdapter {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rv.initGrid(
            mainAdapter,
            GridLayoutManager(requireContext(), 2)
        )
    }


}