package ca.cuvillon.remote

import ca.cuvillon.model.dtos.TeamDto

class TeamDataSource(private val teamService: TeamService) {

    suspend fun getTeams(): List<TeamDto> {
        return teamService.getTeams()
    }
}
