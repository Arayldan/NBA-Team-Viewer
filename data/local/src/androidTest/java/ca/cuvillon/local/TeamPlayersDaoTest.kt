package ca.cuvillon.local

import ca.cuvillon.local.base.BaseDaoTest
import ca.cuvillon.local.dao.PlayerDao
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.local.dao.TeamPlayersDao
import ca.cuvillon.common_test.datasets.TeamAndPlayersDataSet
import ca.cuvillon.common_test.datasets.TeamDataSet
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.koin.test.inject

class TeamPlayersDaoTest : BaseDaoTest() {

    private val teamPlayersDao: TeamPlayersDao by inject()
    private val teamDao: TeamDao by inject()
    private val playerDao: PlayerDao by inject()

    private val team = TeamAndPlayersDataSet.teamAndPlayersA.team
    private val players = TeamAndPlayersDataSet.teamAndPlayersA.players

    override fun setUp() = runBlocking {
        super.setUp()

        // Insert a team and its players
        teamDao.insert(team)
        playerDao.insertAll(players)
    }

    @Test
    fun testGetTeamAndPlayersForTeam() = runBlocking {
        val teamAndPlayers = teamPlayersDao.getTeamAndPlayersForTeam(team.id)
        assertNotNull(teamAndPlayers)
        assertEquals(team, teamAndPlayers!!.team)
        assertEquals(players, teamAndPlayers.players)
    }

    @Test
    fun testGetTeamAndPlayersForTeam_notFound() = runBlocking {
        assertNull(teamPlayersDao.getTeamAndPlayersForTeam(TeamDataSet.UNKNOWN_TEAM_ID))
    }

    @Test
    fun testInsert() = runBlocking {
        val teamAndPlayers = TeamAndPlayersDataSet.teamAndPlayersB
        assertNull(teamPlayersDao.getTeamAndPlayersForTeam(teamAndPlayers.team.id))
        teamPlayersDao.insert(listOf(teamAndPlayers))
        assertEquals(teamAndPlayers, teamPlayersDao.getTeamAndPlayersForTeam(teamAndPlayers.team.id))
    }
}
