package ibm2187.dev.pixabay.common.base.ui

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    abstract val _BindingInflater: (LayoutInflater) -> VB
}