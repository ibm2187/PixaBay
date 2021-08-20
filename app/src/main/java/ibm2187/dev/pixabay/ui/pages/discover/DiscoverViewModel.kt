package ibm2187.dev.pixabay.ui.pages.discover

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ibm2187.dev.pixabay.common.base.wrappers.ResponseWrapper
import ibm2187.dev.pixabay.common.utils.ext.asLiveData
import ibm2187.dev.pixabay.model.interactors.DiscoveryInteractor
import ibm2187.dev.pixabay.model.network.responses.PixaBayResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoveryInteractor: DiscoveryInteractor
) : ViewModel() {

    private val _discoveryObservable = MutableLiveData<ResponseWrapper<PixaBayResponse>>()
    val discoveryObservable get() = _discoveryObservable.asLiveData()
    private val TAG = "TAG"
    fun getDiscover() {
        viewModelScope.launch {
            discoveryInteractor.getEditorsChoice().collect {
                Log.d(TAG, "getDiscover: $it")
                _discoveryObservable.postValue(it)
            }
        }
    }
}