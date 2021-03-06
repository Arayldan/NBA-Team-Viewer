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
        appDatabase.teamDao().deleteAll() // Delete all teams before inserting new teams
        appDatabase.teamDao().insertAll(items.map { it.team })
        appDatabase.playerDao().insertAll(items.flatMap { it.players })
    }

    @Transaction
    @Query("SELECT * FROM team WHERE id = :teamId")
    abstract suspend fun getTeamAndPlayersForTeam(teamId: Int): TeamAndPlayers?
}
