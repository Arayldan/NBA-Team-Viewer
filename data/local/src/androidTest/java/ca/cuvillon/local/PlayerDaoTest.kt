package ca.cuvillon.local

import android.database.sqlite.SQLiteConstraintException
import ca.cuvillon.local.base.BaseDaoTest
import ca.cuvillon.local.dao.PlayerDao
import ca.cuvillon.model.entities.Player
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PlayerDaoTest : BaseDaoTest() {

    private lateinit var playerDao: PlayerDao

    override fun setUp() {
        super.setUp()
        playerDao = database.playerDao()
    }

    @Test(expected = SQLiteConstraintException::class)
    fun testInsert_playerWithoutTeamInDatabase() = runBlocking {
        val playerWithoutTeamInDatabase =
            Player(id = -1, firstName = "A", lastName = "B", number = 22, position = "C", teamId = -1)
        playerDao.insert(playerWithoutTeamInDatabase)
    }
}
