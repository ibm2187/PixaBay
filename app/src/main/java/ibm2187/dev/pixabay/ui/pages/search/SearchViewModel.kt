package ibm2187.dev.pixabay.ui.pages.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ibm2187.dev.pixabay.common.base.wrappers.ResponseWrapper
import ibm2187.dev.pixabay.common.utils.ext.asLiveData
import ibm2187.dev.pixabay.model.interactors.SearchInteractor
import ibm2187.dev.pixabay.model.network.responses.PixaBayResponse
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val _searchObservable = MutableLiveData<ResponseWrapper<PixaBayResponse>>()
    val searchObservable get() = _searchObservable.asLiveData()

    private var queryString: String = ""

    fun setQuery(query: String?) {
        queryString = query ?: ""
    }

    fun search() {
        viewModelScope.launch {
            searchInteractor.search(queryString).collect {
                _searchObservable.postValue(it)
            }
        }
    }
}