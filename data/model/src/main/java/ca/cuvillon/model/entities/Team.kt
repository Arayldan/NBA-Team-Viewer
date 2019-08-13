package ca.cuvillon.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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
    val losses: Int
)
