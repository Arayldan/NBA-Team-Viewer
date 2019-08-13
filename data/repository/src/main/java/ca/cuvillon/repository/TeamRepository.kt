package ca.cuvillon.repository

import androidx.lifecycle.LiveData
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.model.dtos.TeamDto
import ca.cuvillon.model.dtos.toEntities
import ca.cuvillon.model.entities.Team
import ca.cuvillon.remote.TeamDataSource
import ca.cuvillon.repository.utils.NetworkResource
import ca.cuvillon.repository.utils.Resource
import java.util.Date

interface TeamRepository {
    suspend fun getTeams(forceRefresh: Boolean = false): LiveData<Resource<List<Team>>>
}

internal class TeamRepositoryImpl(
    private val dataSource: TeamDataSource,
    private val dao: TeamDao
) : TeamRepository {

    override suspend fun getTeams(forceRefresh: Boolean): LiveData<Resource<List<Team>>> {
        return object : NetworkResource<List<Team>, List<TeamDto>>() {
            override suspend fun loadFromDb(): List<Team> {
                return dao.getAll()
            }

            override suspend fun shouldFetch(): Boolean {
                return forceRefresh || dao.shouldRefresh()
            }

            override suspend fun fetch(): List<TeamDto> {
                return dataSource.getTeams()
            }

            override suspend fun saveRequest(request: List<TeamDto>) {
                val (teams, players) = request.toEntities(lastRefreshed = Date())
                dao.saveTeamsAndPlayers(teams, players)
            }
        }.build().asLiveData()
    }
}
