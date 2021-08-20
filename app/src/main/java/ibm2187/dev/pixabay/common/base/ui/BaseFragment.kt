package ibm2187.dev.pixabay.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    abstract val _BindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    //this is to optimize memory, _binding is reference to view and if you navigate,
    //you will want to remove your view reference to save memory
    //downside is you'll have to save state
    //good side is you can access binding without nulls + your memory is tidy
    //IMPORTANT never use binding (not _binding) before onViewCreated or after onDestroyView unless
    //you like NPE
    private var _binding: VB? = null
    val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = _BindingInflater(layoutInflater, container, false)
        initOnBackPressedListener()
        return binding.root
    }

    private fun initOnBackPressedListener() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })
    }

    //for nav components
    //can be overriden if need be
    open fun onBackPressed() {
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}