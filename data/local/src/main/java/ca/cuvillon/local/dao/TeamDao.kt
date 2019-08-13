package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import ca.cuvillon.model.Team

@Dao
interface TeamDao : BaseDao<Team> {

    @Query("SELECT * FROM team ORDER BY name ASC")
    suspend fun getAll(): List<Team>

    @Query("SELECT * FROM team WHERE id = :id")
    suspend fun findById(id: Int): Team?
}
