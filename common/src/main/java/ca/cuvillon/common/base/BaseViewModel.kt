package ca.cuvillon.common.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import ca.cuvillon.common.utils.Event
import ca.cuvillon.navigation.NavigationCommand

abstract class BaseViewModel : ViewModel() {
    // NAVIGATION
    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> = _navigation

    // ERROR
    private val _snackBarError = MutableLiveData<Event<@StringRes Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackBarError

    /**
     * Handle navigation directly from [ViewModel].
     */
    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }

    /**
     * Handle back navigation.
     */
    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    /**
     * Display the error message in a snackBar.
     */
    protected fun handleError(@StringRes stringRes: Int) {
        _snackBarError.value = Event(stringRes)
    }
}
