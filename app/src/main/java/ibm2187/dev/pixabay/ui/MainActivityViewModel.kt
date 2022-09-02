package ibm2187.dev.pixabay.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ibm2187.core.utils.ext.asLiveData
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor() : ViewModel() {

    private val _toolbarLiftedObservable = MutableLiveData<Boolean>()
    val toolbarLiftedObservable = _toolbarLiftedObservable.asLiveData()
    fun setToolbarLifted(isLifted: Boolean) {
        _toolbarLiftedObservable.postValue(isLifted)
    }
}