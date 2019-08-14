package ca.cuvillon.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Team(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "full_name")
    val name: String,

    @ColumnInfo(name = "wins")
    val wins: Int,

    @ColumnInfo(name = "losses")
    val losses: Int,

    @ColumnInfo(name = "last_refreshed")
    val lastRefreshed: Date
) {
    /**
     * Shared sealed class to specify the order for a [List] of teams.
     */
    sealed class OrderBy {
        object Name : OrderBy()
        object Win : OrderBy()
        object Loss : OrderBy()
    }
}
