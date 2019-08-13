package ca.cuvillon.model.entities

import androidx.room.Embedded
import androidx.room.Relation

data class TeamAndPlayers(
    @Embedded
    val team: Team,

    @Relation(parentColumn = "id", entityColumn = "team_id", entity = Player::class)
    val players: List<Player>
)
