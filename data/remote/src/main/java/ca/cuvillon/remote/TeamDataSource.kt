package ca.cuvillon.remote

import ca.cuvillon.model.Team

class TeamDataSource(private val teamService: TeamService) {

    suspend fun getTeams(): List<Team> {
        return teamService.getTeams()
    }
}
