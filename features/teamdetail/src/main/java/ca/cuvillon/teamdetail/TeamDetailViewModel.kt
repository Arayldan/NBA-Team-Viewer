package ca.cuvillon.teamdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.cuvillon.common.base.BaseViewModel
import ca.cuvillon.common.utils.Event
import ca.cuvillon.model.entities.TeamAndPlayers
import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.repository.utils.Resource
import ca.cuvillon.teamdetail.domain.GetTeamDetailUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates

internal class TeamDetailViewModel(
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private var argsTeamId: Int by Delegates.notNull()

    private val _teamAndPlayers = MediatorLiveData<TeamAndPlayers>()
    val teamAndPlayers: LiveData<TeamAndPlayers> get() = _teamAndPlayers
    private var teamAndPlayersSource: LiveData<Resource<TeamAndPlayers>> = MutableLiveData()

    fun loadData(teamId: Int) {
        argsTeamId = teamId
        getTeam()
    }

    private fun getTeam() = viewModelScope.launch(dispatchers.main) {
        _teamAndPlayers.removeSource(teamAndPlayersSource)
        withContext(dispatchers.io) { teamAndPlayersSource = getTeamDetailUseCase(argsTeamId) }
        _teamAndPlayers.addSource(teamAndPlayersSource) {
            it?.data?.let(_teamAndPlayers::setValue)
            when {
                it is Resource.Error -> {
                    _snackbarError.value = Event(R.string.snack_default_error_message)
                }
                it is Resource.Success && it.data == null -> {
                    _snackbarError.value = Event(R.string.snack_no_team_found_error_message)
                    navigateBack()
                }
            }
        }
    }
}
