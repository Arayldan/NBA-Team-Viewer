package ca.cuvillon.repository.utils

import androidx.annotation.WorkerThread
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

internal abstract class NetworkResource<Result, Remote, Local> {

    private val result = MutableLiveData<Resource<Result>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkResource<Result, Remote, Local> {
        withContext(Dispatchers.Main) {
            result.value = Resource.Loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)) {
                try {
                    fetchFromNetwork()
                } catch (e: IOException) {
                    Timber.e(e, "An error happened")
                    setValue(Resource.Error(e, loadFromDb()))
                }
            } else {
                Timber.d("Return data from local database")
                setValue(Resource.Success(dbResult))
            }
        }
        return this
    }

    fun asLiveData(): LiveData<Resource<Result>> {
        return result
    }

    private suspend fun fetchFromNetwork() {
        Timber.d("Fetch data from network")
        setValue(Resource.Loading(loadFromDb())) // Quickly dispatch local data during loading
        val apiResponse = fetch()
        Timber.i("Data fetched from network")
        saveResult(withContext(Dispatchers.IO) {
            processResponse(apiResponse)
        })
        setValue(Resource.Success(loadFromDb()))
    }

    private fun setValue(newValue: Resource<Result>) {
        Timber.d("setValue(%s)", newValue)
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    protected abstract suspend fun loadFromDb(): Result?

    protected abstract suspend fun shouldFetch(data: Result?): Boolean

    protected abstract suspend fun fetch(): Remote

    protected abstract suspend fun saveResult(result: Local)

    @WorkerThread
    protected abstract fun processResponse(response: Remote): Local
}
