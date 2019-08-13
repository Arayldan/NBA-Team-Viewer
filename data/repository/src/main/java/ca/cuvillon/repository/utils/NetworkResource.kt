package ca.cuvillon.repository.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.IOException
import kotlin.coroutines.coroutineContext

internal abstract class NetworkResource<Result, Request> {

    private val result = MutableLiveData<Resource<Result>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkResource<Result, Request> {
        withContext(Dispatchers.Main) {
            result.value = Resource.Loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            try {
                fetchFromNetwork()
            } catch (e: IOException) {
                Timber.e(e, "An error happened")
                setValue(Resource.Error(e, loadFromDb()))
            }
        }
        return this
    }

    fun asLiveData(): LiveData<Resource<Result>> {
        return result
    }

    private suspend fun fetchFromNetwork() {
        Timber.d("Fetch data from network")
        val apiResponse = fetch()
        Timber.i("Data fetched from network")
        withContext(Dispatchers.IO) { saveRequest(apiResponse) }
        setValue(Resource.Success(loadFromDb()))
    }

    private fun setValue(newValue: Resource<Result>) {
        Timber.d("setValue(%s)", newValue)
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    protected abstract suspend fun loadFromDb(): Result

    protected abstract suspend fun fetch(): Request

    protected abstract suspend fun saveRequest(request: Request)
}
