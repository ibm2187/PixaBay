package ibm2187.dev.pixabay.model.interactors

import ibm2187.dev.pixabay.common.base.interactors.BaseNetworkInteractor
import ibm2187.dev.pixabay.model.network.api.PixabayApi

class SearchInteractor(
    private val api: PixabayApi
) : BaseNetworkInteractor() {

    fun search(query: String) = safeApiCall {
        api.search(query)
    }

}