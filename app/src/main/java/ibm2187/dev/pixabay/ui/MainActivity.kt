package ibm2187.dev.pixabay.ui

import android.view.LayoutInflater
import dagger.hilt.android.AndroidEntryPoint
import ibm2187.dev.pixabay.common.base.ui.BaseActivity
import ibm2187.dev.pixabay.databinding.ActivityMainBinding


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val _BindingInflater: (LayoutInflater) -> ActivityMainBinding
            by lazy { ActivityMainBinding::inflate }
}