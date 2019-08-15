package ca.cuvillon.local

import ca.cuvillon.local.base.BaseDaoTest
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.local.utils.testEarliestDate
import ca.cuvillon.local.utils.testLatestDate
import ca.cuvillon.model.entities.Team
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.koin.test.inject

class TeamDaoTest : BaseDaoTest() {

    private val teamDao: TeamDao by inject()

    private val teamA = Team(id = 1, name = "A", wins = 0, losses = 3, lastRefreshed = testEarliestDate)
    private val teamB = Team(id = 2, name = "B", wins = 1, losses = 2, lastRefreshed = testLatestDate)
    private val teamC = Team(id = 3, name = "C", wins = 2, losses = 1, lastRefreshed = testLatestDate)

    override fun setUp() = runBlocking {
        super.setUp()

        // Insert teams not ordered by name asc, nor by win desc, nor by loss desc
        teamDao.insertAll(listOf(teamB, teamA, teamC))
    }

    @Test
    fun testGetAllOrderedByName() = runBlocking {
        val teamList = teamDao.getAllOrderedByName()
        assertEquals(3, teamList.size)

        // Ensure team list is sorted by name
        assertEquals(teamA, teamList[0])
        assertEquals(teamB, teamList[1])
        assertEquals(teamC, teamList[2])
    }

    @Test
    fun testGetAllOrderedByWin() = runBlocking {
        val teamList = teamDao.getAllOrderedByWin()
        assertEquals(3, teamList.size)

        // Ensure team list is sorted by win
        assertEquals(teamC, teamList[0])
        assertEquals(teamB, teamList[1])
        assertEquals(teamA, teamList[2])
    }

    @Test
    fun testGetAllOrderedByLoss() = runBlocking {
        val teamList = teamDao.getAllOrderedByLoss()
        assertEquals(3, teamList.size)

        // Ensure team list is sorted by loss
        assertEquals(teamA, teamList[0])
        assertEquals(teamB, teamList[1])
        assertEquals(teamC, teamList[2])
    }

    @Test
    fun testDeleteAll() = runBlocking {
        // Ensure table is not already empty
        assertTrue(teamDao.getAllOrderedByName().isNotEmpty())

        // Clear table
        teamDao.deleteAll()

        // Ensure table is now empty
        assertTrue(teamDao.getAllOrderedByName().isEmpty())
    }

    @Test
    fun testGetMinimalLastRefreshed() = runBlocking {
        assertEquals(testEarliestDate, teamDao.getMinimalLastRefreshed())
    }

    @Test
    fun testGetMinimalLastRefreshed_notFound() = runBlocking {
        // Clear table to make sure no minimalLastRefreshed date is available
        teamDao.deleteAll()
        assertNull(teamDao.getMinimalLastRefreshed())
    }
}
