package ibm2187.dev.pixabay.ui.pages.discover

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.ibm2187.core.utils.ext.asLiveData
import ibm2187.dev.pixabay.common.base.wrappers.ResponseWrapper
import ibm2187.dev.pixabay.model.interactors.DiscoveryInteractor
import dev.ibm2187.pixabay.core.data.network.responses.PixaBayResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    private val discoveryInteractor: DiscoveryInteractor
) : ViewModel() {

    private val _discoveryObservable = MutableLiveData<ResponseWrapper<PixaBayResponse>>()
    val discoveryObservable get() = _discoveryObservable.asLiveData()
    fun getDiscover() {
        viewModelScope.launch {
            discoveryInteractor.getEditorsChoice().collect {
                _discoveryObservable.postValue(it)
            }
        }
    }
}