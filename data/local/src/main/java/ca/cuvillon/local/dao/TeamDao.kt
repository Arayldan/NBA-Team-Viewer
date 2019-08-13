package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import ca.cuvillon.model.entities.Team
import java.util.Date

@Dao
interface TeamDao : BaseDao<Team> {

    @Query("SELECT * FROM team ORDER BY full_name ASC")
    suspend fun getAll(): List<Team>

    @Query("DELETE FROM team")
    suspend fun clear()

    @Query("SELECT MIN(last_refreshed) FROM team")
    suspend fun findMinimalLastRefreshed(): Date?
}
