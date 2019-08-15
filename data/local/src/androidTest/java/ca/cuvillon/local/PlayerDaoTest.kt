package ca.cuvillon.local

import android.database.sqlite.SQLiteConstraintException
import ca.cuvillon.local.base.BaseDaoTest
import ca.cuvillon.local.dao.PlayerDao
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.common_test.datasets.PlayerDataSet
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject

class PlayerDaoTest : BaseDaoTest() {

    private val playerDao: PlayerDao by inject()
    private val teamDao: TeamDao by inject()

    @Test(expected = SQLiteConstraintException::class)
    fun testInsert_playerWithoutTeamInDatabase() = runBlocking {
        teamDao.deleteAll()
        playerDao.insert(PlayerDataSet.playerA)
    }
}
