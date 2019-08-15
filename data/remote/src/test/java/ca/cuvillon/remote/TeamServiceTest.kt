package ca.cuvillon.remote

import ca.cuvillon.remote.base.BaseServiceTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test
import org.koin.test.inject
import retrofit2.HttpException
import java.net.HttpURLConnection

class TeamServiceTest : BaseServiceTest() {

    private val teamService: TeamService by inject()

    @Test
    fun testGetTeams() = runBlocking {
        mockHttpResponse(FakeEndpoints.GET_TEAMS)

        val teams = teamService.getTeams()
        assertNotNull(teams)
        assertEquals(30, teams.size)

        val team = teams.first()
        assertEquals(1, team.id)
        assertEquals("Boston Celtics", team.name)
        assertEquals(45, team.wins)
        assertEquals(20, team.losses)

        val players = team.players
        assertNotNull(players)
        assertEquals(17, players.size)

        val player = players.first()
        assertEquals(37729, player.id)
        assertEquals("Kadeem", player.firstName)
        assertEquals("Allen", player.lastName)
        assertEquals(45, player.number)
        assertEquals("SG", player.position)
    }

    @Test(expected = HttpException::class)
    fun testGetTeams_andFail() = runBlocking {
        mockHttpResponse(FakeEndpoints.GET_TEAMS, HttpURLConnection.HTTP_FORBIDDEN)
        teamService.getTeams()
        fail()
    }
}
