package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ca.cuvillon.local.AppDatabase
import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team

@Dao
abstract class TeamDao(private val appDatabase: AppDatabase) : BaseDao<Team> {

    @Query("SELECT * FROM team ORDER BY full_name ASC")
    abstract suspend fun getAll(): List<Team>

    @Query("SELECT * FROM team WHERE id = :id")
    abstract suspend fun findById(id: Int): Team?

    @Transaction
    open suspend fun saveTeamsAndPlayers(teams: List<Team>, players: List<Player>) {
        insert(teams)
        appDatabase.playerDao().insert(players)
    }
}
