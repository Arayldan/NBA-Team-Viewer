package ca.cuvillon.teamdetail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ca.cuvillon.model.entities.Team
import ca.cuvillon.repository.TeamRepository
import ca.cuvillon.repository.utils.Resource

internal class GetTeamDetailUseCase(private val repository: TeamRepository) {

    suspend operator fun invoke(forceRefresh: Boolean = false, teamId: Int): LiveData<Resource<Team>> {
        return Transformations.map(repository.getTeam(forceRefresh, teamId)) {
            it // TODO Use ViewState
        }
    }
}
