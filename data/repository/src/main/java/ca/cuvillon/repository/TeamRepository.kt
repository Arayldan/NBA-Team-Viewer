package ca.cuvillon.repository

import androidx.lifecycle.LiveData
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.local.dao.TeamPlayersDao
import ca.cuvillon.model.dtos.TeamDto
import ca.cuvillon.model.dtos.toTeamAndPlayersEntity
import ca.cuvillon.model.entities.Team
import ca.cuvillon.model.entities.TeamAndPlayers
import ca.cuvillon.remote.TeamDataSource
import ca.cuvillon.repository.utils.NetworkResource
import ca.cuvillon.repository.utils.Resource
import java.util.Date
import java.util.concurrent.TimeUnit

interface TeamRepository {
    suspend fun getTeams(forceRefresh: Boolean = false, orderBy: Team.OrderBy): LiveData<Resource<List<Team>>>
    suspend fun getTeamAndPlayers(id: Int): LiveData<Resource<TeamAndPlayers>>
}

internal class TeamRepositoryImpl(
    private val dataSource: TeamDataSource,
    private val teamDao: TeamDao,
    private val teamPlayersDao: TeamPlayersDao
) : TeamRepository {

    override suspend fun getTeams(forceRefresh: Boolean, orderBy: Team.OrderBy): LiveData<Resource<List<Team>>> {
        return object : NetworkResource<List<Team>, List<TeamDto>, List<TeamAndPlayers>>() {
            override suspend fun loadFromDb(): List<Team>? {
                return when (orderBy) {
                    Team.OrderBy.Name -> teamDao.getAllOrderedByName()
                    Team.OrderBy.Win -> teamDao.getAllOrderedByWin()
                    Team.OrderBy.Loss -> teamDao.getAllOrderedByLoss()
                }
            }

            override suspend fun shouldFetch(data: List<Team>?): Boolean {
                return forceRefresh || shouldRefresh(teamDao.getMinimalLastRefreshed())
            }

            override suspend fun fetch(): List<TeamDto> {
                return dataSource.getTeams()
            }

            override suspend fun saveResult(result: List<TeamAndPlayers>) {
                teamPlayersDao.insert(result)
            }

            override fun processResponse(response: List<TeamDto>): List<TeamAndPlayers> {
                return response.toTeamAndPlayersEntity(lastRefreshed = Date())
            }
        }.build().asLiveData()
    }

    override suspend fun getTeamAndPlayers(id: Int): LiveData<Resource<TeamAndPlayers>> {
        return object : NetworkResource<TeamAndPlayers, List<TeamDto>, List<TeamAndPlayers>>() {
            override suspend fun loadFromDb(): TeamAndPlayers? {
                return teamPlayersDao.getTeamAndPlayersForTeam(id)
            }

            override suspend fun shouldFetch(data: TeamAndPlayers?): Boolean {
                return data == null || shouldRefresh(data.team.lastRefreshed)
            }

            override suspend fun fetch(): List<TeamDto> {
                return dataSource.getTeams()
            }

            override suspend fun saveResult(result: List<TeamAndPlayers>) {
                teamPlayersDao.insert(result)
            }

            override fun processResponse(response: List<TeamDto>): List<TeamAndPlayers> {
                return response.toTeamAndPlayersEntity(lastRefreshed = Date())
            }
        }.build().asLiveData()
    }

    companion object {
        private const val FRESH_TIME_IN_MINUTES = 10

        private fun shouldRefresh(lastRefreshed: Date?): Boolean {
            return lastRefreshed == null ||
                TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= FRESH_TIME_IN_MINUTES
        }
    }
}
