package ca.cuvillon.remote

import ca.cuvillon.model.Team
import retrofit2.http.GET

interface TeamService {

    @GET("input.json")
    suspend fun getTeams(): List<Team>
}
