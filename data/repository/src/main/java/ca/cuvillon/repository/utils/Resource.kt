package ca.cuvillon.repository.utils

sealed class Resource<out T>(val data: T?) {
    class Success<T>(data: T?) : Resource<T>(data)

    class Error<T>(val error: Throwable, data: T?) : Resource<T>(data)

    class Loading<T>(data: T?) : Resource<T>(data)
}
