package ca.cuvillon.model.dtos

import ca.cuvillon.model.entities.Player
import com.google.gson.annotations.SerializedName

data class PlayerDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("first_name")
    val firstName: String,

    @SerializedName("last_name")
    val lastName: String,

    @SerializedName("position")
    val position: String,

    @SerializedName("number")
    val number: Int
)

fun PlayerDto.toEntity(teamId: Int): Player {
    return Player(
        id = id,
        firstName = firstName,
        lastName = lastName,
        position = position,
        number = number,
        teamId = teamId
    )
}

fun List<PlayerDto>.toEntity(teamId: Int): List<Player> {
    return map { it.toEntity(teamId) }
}
