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
    suspend fun getTeams(forceRefresh: Boolean = false): LiveData<Resource<List<Team>>>
    suspend fun getTeamAndPlayers(forceRefresh: Boolean = false, id: Int): LiveData<Resource<TeamAndPlayers>>
}

internal class TeamRepositoryImpl(
    private val dataSource: TeamDataSource,
    private val teamDao: TeamDao,
    private val teamPlayersDao: TeamPlayersDao
) : TeamRepository {

    override suspend fun getTeams(forceRefresh: Boolean): LiveData<Resource<List<Team>>> {
        return object : NetworkResource<List<Team>, List<TeamDto>>() {
            override suspend fun loadFromDb(): List<Team> {
                return teamDao.getAll()
            }

            override suspend fun shouldFetch(): Boolean {
                return forceRefresh || shouldRefresh(teamDao.findMinimalLastRefreshed())
            }

            override suspend fun fetch(): List<TeamDto> {
                return dataSource.getTeams()
            }

            override suspend fun saveRequest(request: List<TeamDto>) {
                teamPlayersDao.insert(request.toTeamAndPlayersEntity(lastRefreshed = Date()))
            }
        }.build().asLiveData()
    }

    override suspend fun getTeamAndPlayers(forceRefresh: Boolean, id: Int): LiveData<Resource<TeamAndPlayers>> {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        private const val FRESH_TIME_IN_MINUTES = 10

        private fun shouldRefresh(lastRefreshed: Date?): Boolean {
            return lastRefreshed == null ||
                TimeUnit.MILLISECONDS.toMinutes(Date().time - lastRefreshed.time) >= FRESH_TIME_IN_MINUTES
        }
    }
}
