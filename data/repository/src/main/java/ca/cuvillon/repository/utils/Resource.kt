package ca.cuvillon.repository.utils

sealed class Resource<out T>(open val data: T?) {
    data class Success<T>(override val data: T?) : Resource<T>(data)

    data class Error<T>(val error: Throwable, override val data: T?) : Resource<T>(data)

    data class Loading<T>(override val data: T?) : Resource<T>(data)
}
