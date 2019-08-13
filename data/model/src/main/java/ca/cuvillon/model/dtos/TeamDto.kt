package ca.cuvillon.model.dtos

import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team
import com.google.gson.annotations.SerializedName

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

fun TeamDto.toEntity(): Team {
    return Team(id = id, name = name, wins = wins, losses = losses)
}

fun List<TeamDto>.toEntity(): List<Team> {
    return map { it.toEntity() }
}

fun TeamDto.toEntities(): Pair<Team, List<Player>> {
    return toEntity() to players.toEntity(id)
}

fun List<TeamDto>.toEntities(): Pair<List<Team>, List<Player>> {
    return flatMap { toEntity() } to flatMap { it.players.toEntity(it.id) }
}
