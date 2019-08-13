package ca.cuvillon.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Team(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("full_name")
    val name: String,

    @SerializedName("wins")
    val wins: Int,

    @SerializedName("losses")
    val losses: Int,

    @SerializedName("players")
    @Embedded(prefix = "player_")
    val players: List<Player>
)
