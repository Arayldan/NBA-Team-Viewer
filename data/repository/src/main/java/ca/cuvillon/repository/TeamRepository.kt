package ca.cuvillon.repository

import androidx.lifecycle.LiveData
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.model.Team
import ca.cuvillon.remote.TeamDataSource
import ca.cuvillon.repository.utils.NetworkResource
import ca.cuvillon.repository.utils.Resource

interface TeamRepository {
    suspend fun getTeams(forceRefresh: Boolean = false): LiveData<Resource<List<Team>>>
}

internal class TeamRepositoryImpl(
    private val dataSource: TeamDataSource,
    private val dao: TeamDao
) : TeamRepository {

    override suspend fun getTeams(forceRefresh: Boolean): LiveData<Resource<List<Team>>> {
        return object : NetworkResource<List<Team>, List<Team>>() {
            override suspend fun loadFromDb(): List<Team> {
                return dao.getAll()
            }

            override suspend fun fetch(): List<Team> {
                return dataSource.getTeams()
            }

            override suspend fun saveResult(result: List<Team>) {
                dao.insert(result)
            }

            override fun processResponse(response: List<Team>): List<Team> {
                return response
            }
        }.build().asLiveData()
    }
}
