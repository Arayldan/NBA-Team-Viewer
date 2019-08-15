package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import ca.cuvillon.model.entities.Team
import java.util.Date

@Dao
interface TeamDao : BaseDao<Team> {

    @Query("SELECT * FROM team ORDER BY full_name ASC")
    suspend fun getAllOrderedByName(): List<Team>

    @Query("SELECT * FROM team ORDER BY wins DESC")
    suspend fun getAllOrderedByWin(): List<Team>

    @Query("SELECT * FROM team ORDER BY losses DESC")
    suspend fun getAllOrderedByLoss(): List<Team>

    @Query("DELETE FROM team")
    suspend fun deleteAll()

    @Query("SELECT MIN(last_refreshed) FROM team")
    suspend fun getMinimalLastRefreshed(): Date?
}
