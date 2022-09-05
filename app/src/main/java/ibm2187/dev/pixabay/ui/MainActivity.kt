package ibm2187.dev.pixabay.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import dagger.hilt.android.AndroidEntryPoint
import ibm2187.dev.pixabay.R
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

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            binding.appBarLayout.setLiftable(destination.id != R.id.discoverFragment)
        }

    }

    override fun onBackPressed() {
        if (navController.previousBackStackEntry == null)
            finish()
        else {
            super.onBackPressed()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}