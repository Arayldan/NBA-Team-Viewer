package ca.cuvillon.teamlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.cuvillon.common.base.BaseViewModel
import ca.cuvillon.common.utils.Event
import ca.cuvillon.model.entities.Team
import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.repository.utils.Resource
import ca.cuvillon.teamlist.domain.GetTeamsUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * A [BaseViewModel] subclass that provide the data and handle logic to communicate with the model
 * for [TeamListFragment].
 */
internal class TeamListViewModel(
    private val getTeamsUseCase: GetTeamsUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val _teams = MediatorLiveData<Resource<List<Team>>>()
    val teams: LiveData<Resource<List<Team>>> get() = _teams
    private var teamsSource: LiveData<Resource<List<Team>>> = MutableLiveData()

    private var orderBy: Team.OrderBy = Team.OrderBy.Name // Default order

    init {
        getTeams(false)
    }

    fun refreshTeams() {
        getTeams(true)
    }

    fun clickOnItem(team: Team) {
        Timber.d("clickOnItem(%s)", team)
        navigate(TeamListFragmentDirections.actionTeamListFragmentToTeamDetailFragment(team.id))
    }

    fun sortTeams(orderBy: Team.OrderBy) {
        this.orderBy = orderBy
        getTeams(false)
    }

    private fun getTeams(forceRefresh: Boolean) = viewModelScope.launch(dispatchers.main) {
        _teams.removeSource(teamsSource)
        withContext(dispatchers.io) { teamsSource = getTeamsUseCase(forceRefresh, orderBy) }
        _teams.addSource(teamsSource) {
            _teams.value = it
            if (it is Resource.Error) {
                _snackbarError.value = Event(R.string.snack_default_error_message)
            }
        }
    }
}
