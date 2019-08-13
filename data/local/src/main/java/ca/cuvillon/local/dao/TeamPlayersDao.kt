package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ca.cuvillon.local.AppDatabase
import ca.cuvillon.model.entities.TeamAndPlayers

@Dao
abstract class TeamPlayersDao(private val appDatabase: AppDatabase) {

    @Transaction
    open suspend fun insert(items: List<TeamAndPlayers>) {
        appDatabase.teamDao().clear() // Delete all teams before inserting new teams
        appDatabase.teamDao().insert(items.map { it.team })
        appDatabase.playerDao().insert(items.flatMap { it.players })
    }

    @Transaction
    @Query("SELECT * FROM team WHERE id = :id")
    abstract suspend fun findTeamWithPlayersById(id: Int): TeamAndPlayers?
}
