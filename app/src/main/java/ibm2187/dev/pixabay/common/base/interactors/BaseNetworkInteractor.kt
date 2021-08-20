package ibm2187.dev.pixabay.common.base.interactors

import ibm2187.dev.pixabay.R
import ibm2187.dev.pixabay.common.base.wrappers.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import retrofit2.Response

open class BaseNetworkInteractor {

    fun <T> safeApiCall(request: (suspend () -> Response<T>)) = flow {
        //show loading state
        emit(ResponseWrapper.Loading)

        kotlin.runCatching {
            request.invoke()
        }.onSuccess {
            println("response = $it")
            if (it.isSuccessful) {
                emit(ResponseWrapper.Success(it.body()))
            } else {
                emit(ResponseWrapper.Failure(it.message()))
            }
        }.onFailure {
            val errorMsg = catchException(it)
            emit(ResponseWrapper.LocalFailure(errorMsg))
        }
    }.flowOn(Dispatchers.IO)

    private fun catchException(throwable: Throwable) = when (throwable) {
        is HttpException -> R.string.error_internet_failure
        else -> R.string.error_service_failure
    }
}
