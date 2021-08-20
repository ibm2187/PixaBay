package ibm2187.dev.pixabay.common.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val _BindingInflater: (LayoutInflater) -> VB

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = _BindingInflater(layoutInflater)
        setContentView(binding.root)

    }

    fun initNavController(fragmentContainerView: FragmentContainerView) {
        supportFragmentManager.findFragmentById(fragmentContainerView.id)
            ?.findNavController()
            ?.let {
                navController = it
            }
    }

    open fun initToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}