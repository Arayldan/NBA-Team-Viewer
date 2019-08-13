package ca.cuvillon.teamdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import ca.cuvillon.common.base.BaseViewModel
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

    private val _team = MediatorLiveData<Resource<TeamAndPlayers>>()
    val team: LiveData<Resource<TeamAndPlayers>> get() = _team
    private var teamSource: LiveData<Resource<TeamAndPlayers>> = MutableLiveData()

    fun loadDate(teamId: Int) {
        argsTeamId = teamId
        getTeam(false)
    }

    private fun getTeam(forceRefresh: Boolean) = viewModelScope.launch(dispatchers.main) {
        _team.removeSource(teamSource)
        withContext(dispatchers.io) { teamSource = getTeamDetailUseCase(forceRefresh, argsTeamId) }
        _team.addSource(teamSource) {
            _team.value = it
        }
    }
}
