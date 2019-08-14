package ca.cuvillon.common.base

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
    protected val _snackbarError = MutableLiveData<Event<@androidx.annotation.StringRes Int>>()
    val snackBarError: LiveData<Event<Int>> get() = _snackbarError

    /**
     * Handle navigation directly from [ViewModel].
     */
    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.To(directions))
    }
}
