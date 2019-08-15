package ca.cuvillon.local

import ca.cuvillon.local.base.BaseDaoTest
import ca.cuvillon.local.dao.PlayerDao
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.local.dao.TeamPlayersDao
import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team
import ca.cuvillon.model.entities.TeamAndPlayers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test
import org.koin.test.inject
import java.util.Date

class TeamPlayersDaoTest : BaseDaoTest() {

    private val teamPlayersDao: TeamPlayersDao by inject()
    private val teamDao: TeamDao by inject()
    private val playerDao: PlayerDao by inject()

    private val team = Team(id = 1, name = "A", wins = 0, losses = 3, lastRefreshed = Date())
    private val players = listOf(
        Player(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10, teamId = 1),
        Player(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20, teamId = 1)
    )

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
        assertNull(teamPlayersDao.getTeamAndPlayersForTeam(-1))
    }

    @Test
    fun testInsert() = runBlocking {
        val teamAndPlayers = TeamAndPlayers(
            team = Team(id = -1, name = "X", wins = 20, losses = 6, lastRefreshed = Date()),
            players = listOf(
                Player(
                    id = -1,
                    firstName = "X1",
                    lastName = "X2",
                    position = "C",
                    number = 78,
                    teamId = -1
                )
            )
        )

        teamPlayersDao.insert(listOf(teamAndPlayers))
        assertEquals(teamAndPlayers, teamPlayersDao.getTeamAndPlayersForTeam(teamAndPlayers.team.id))
    }
}
