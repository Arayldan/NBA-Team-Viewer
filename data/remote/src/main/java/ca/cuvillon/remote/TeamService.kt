package ca.cuvillon.remote

import ca.cuvillon.model.dtos.TeamDto
import retrofit2.http.GET

interface TeamService {

    @GET("input.json")
    suspend fun getTeams(): List<TeamDto>
}
