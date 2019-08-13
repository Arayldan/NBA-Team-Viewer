package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import ca.cuvillon.local.AppDatabase
import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team
import java.util.Date
import java.util.concurrent.TimeUnit

@Dao
abstract class TeamDao(private val appDatabase: AppDatabase) : BaseDao<Team> {

    @Query("SELECT * FROM team ORDER BY full_name ASC")
    abstract suspend fun getAll(): List<Team>

    @Query("SELECT * FROM team WHERE id = :id")
    abstract suspend fun findById(id: Int): Team?

    @Query("DELETE FROM team")
    abstract suspend fun clear()

    @Query("SELECT MIN(last_refreshed) FROM team")
    abstract suspend fun findMinimalLastRefreshed(): Date?

    @Transaction
    open suspend fun saveTeamsAndPlayers(teams: List<Team>, players: List<Player>) {
        clear()
        insert(teams)
        appDatabase.playerDao().insert(players)
    }

    suspend fun shouldRefresh(): Boolean {
        val minimalLastRefreshed = findMinimalLastRefreshed()
        return minimalLastRefreshed == null ||
            TimeUnit.MILLISECONDS.toMinutes(Date().time - minimalLastRefreshed.time) >= FRESH_TIME_IN_MINUTES
    }

    companion object {
        const val FRESH_TIME_IN_MINUTES = 10
    }
}
