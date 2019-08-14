package ca.cuvillon.teamdetail.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ca.cuvillon.model.entities.TeamAndPlayers
import ca.cuvillon.repository.TeamRepository
import ca.cuvillon.repository.utils.Resource

internal class GetTeamDetailUseCase(private val repository: TeamRepository) {

    suspend operator fun invoke(teamId: Int): LiveData<Resource<TeamAndPlayers>> {
        return Transformations.map(repository.getTeamAndPlayers(teamId)) {
            it // TODO Use ViewState
        }
    }
}
