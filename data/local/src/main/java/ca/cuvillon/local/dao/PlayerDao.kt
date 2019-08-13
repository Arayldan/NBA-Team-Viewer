package ca.cuvillon.local.dao

import androidx.room.Dao
import androidx.room.Query
import ca.cuvillon.model.Player

@Dao
interface PlayerDao : BaseDao<Player> {

    @Query("SELECT * FROM player ORDER BY firstName, lastName ASC")
    suspend fun getAll(): List<Player>

    @Query("SELECT * FROM player WHERE id = :id")
    suspend fun findById(id: Int): Player?
}
