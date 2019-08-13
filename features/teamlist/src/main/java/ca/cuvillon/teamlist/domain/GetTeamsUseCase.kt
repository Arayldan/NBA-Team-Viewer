package ca.cuvillon.teamlist.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ca.cuvillon.model.Team
import ca.cuvillon.repository.TeamRepository
import ca.cuvillon.repository.utils.Resource

internal class GetTeamsUseCase(private val repository: TeamRepository) {

    suspend operator fun invoke(forceRefresh: Boolean = false): LiveData<Resource<List<Team>>> {
        return Transformations.map(repository.getTeams(forceRefresh)) {
            it //TODO Use ViewState
        }
    }
}
