package ca.cuvillon.model.dtos

import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team
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

fun TeamDto.toEntity(lastRefreshed: Date): Team {
    return Team(id = id, name = name, wins = wins, losses = losses, lastRefreshed = lastRefreshed)
}

fun List<TeamDto>.toEntity(lastRefreshed: Date): List<Team> {
    return map { it.toEntity(lastRefreshed) }
}

fun TeamDto.toEntities(lastRefreshed: Date): Pair<Team, List<Player>> {
    return toEntity(lastRefreshed) to players.toEntity(id)
}

fun List<TeamDto>.toEntities(lastRefreshed: Date): Pair<List<Team>, List<Player>> {
    return flatMap { toEntity(lastRefreshed) } to flatMap { it.players.toEntity(it.id) }
}
