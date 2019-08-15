package ca.cuvillon.local

import ca.cuvillon.local.base.BaseDaoTest
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.common_test.datasets.TeamDataSet
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.koin.test.inject

class TeamDaoTest : BaseDaoTest() {

    private val teamDao: TeamDao by inject()

    override fun setUp() = runBlocking {
        super.setUp()

        // Insert teams not ordered by name asc, nor by win desc, nor by loss desc
        teamDao.insertAll(listOf(TeamDataSet.teamB, TeamDataSet.teamA, TeamDataSet.teamC))
    }

    @Test
    fun testGetAllOrderedByName() = runBlocking {
        val teamList = teamDao.getAllOrderedByName()
        assertEquals(3, teamList.size)

        // Ensure team list is sorted by name
        assertEquals(TeamDataSet.teamA, teamList[0])
        assertEquals(TeamDataSet.teamB, teamList[1])
        assertEquals(TeamDataSet.teamC, teamList[2])
    }

    @Test
    fun testGetAllOrderedByWin() = runBlocking {
        val teamList = teamDao.getAllOrderedByWin()
        assertEquals(3, teamList.size)

        // Ensure team list is sorted by win
        assertEquals(TeamDataSet.teamC, teamList[0])
        assertEquals(TeamDataSet.teamB, teamList[1])
        assertEquals(TeamDataSet.teamA, teamList[2])
    }

    @Test
    fun testGetAllOrderedByLoss() = runBlocking {
        val teamList = teamDao.getAllOrderedByLoss()
        assertEquals(3, teamList.size)

        // Ensure team list is sorted by loss
        assertEquals(TeamDataSet.teamA, teamList[0])
        assertEquals(TeamDataSet.teamB, teamList[1])
        assertEquals(TeamDataSet.teamC, teamList[2])
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
        assertEquals(TeamDataSet.earliestDate, teamDao.getMinimalLastRefreshed())
    }

    @Test
    fun testGetMinimalLastRefreshed_notFound() = runBlocking {
        // Clear table to make sure no minimalLastRefreshed date is available
        teamDao.deleteAll()
        assertNull(teamDao.getMinimalLastRefreshed())
    }
}
