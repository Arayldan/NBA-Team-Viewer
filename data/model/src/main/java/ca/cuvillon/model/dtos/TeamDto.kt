package ca.cuvillon.model.dtos

import ca.cuvillon.model.entities.Team
import ca.cuvillon.model.entities.TeamAndPlayers
import com.google.gson.annotations.SerializedName
import java.util.Date

data class TeamDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("full_name")
    val name: String,

    @SerializedName("wins")
    val wins: Int,

    @SerializedName("losses")
    val losses: Int,

    @SerializedName("players")
    val players: List<PlayerDto>
)

fun TeamDto.toTeamEntity(lastRefreshed: Date): Team {
    return Team(id = id, name = name, wins = wins, losses = losses, lastRefreshed = lastRefreshed)
}

fun TeamDto.toTeamAndPlayersEntity(lastRefreshed: Date): TeamAndPlayers {
    return TeamAndPlayers(team = toTeamEntity(lastRefreshed), players = players.toEntity(id))
}

fun List<TeamDto>.toTeamAndPlayersEntity(lastRefreshed: Date): List<TeamAndPlayers> {
    return map { it.toTeamAndPlayersEntity(lastRefreshed) }
}
