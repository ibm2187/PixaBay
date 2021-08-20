package ibm2187.dev.pixabay.ui

import android.os.Bundle
import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import ibm2187.dev.pixabay.common.base.ui.BaseActivity
import ibm2187.dev.pixabay.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val _BindingInflater: (LayoutInflater) -> ActivityMainBinding
            by lazy { ActivityMainBinding::inflate }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initNavController(binding.fragmentContainerView)
        initToolbar(binding.toolbar)


    }
}