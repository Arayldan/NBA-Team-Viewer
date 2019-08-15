package ca.cuvillon.model.dtos

import ca.cuvillon.model.entities.Player
import ca.cuvillon.model.entities.Team
import ca.cuvillon.model.entities.TeamAndPlayers
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Date

class TeamDtoTest {

    private val playerDtoA = PlayerDto(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10)
    private val playerA = Player(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10, teamId = 1)

    private val playerDtoB = PlayerDto(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20)
    private val playerB = Player(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20, teamId = 2)

    private val lastRefreshedDate = Date()

    private val teamDtoA = TeamDto(id = 1, name = "A", wins = 0, losses = 3, players = listOf(playerDtoA))
    private val teamA = Team(id = 1, name = "A", wins = 0, losses = 3, lastRefreshed = lastRefreshedDate)
    private val teamAndPlayersA = TeamAndPlayers(team = teamA, players = listOf(playerA))

    private val teamDtoB = TeamDto(id = 2, name = "B", wins = 1, losses = 2, players = listOf(playerDtoB))
    private val teamB = Team(id = 2, name = "B", wins = 1, losses = 2, lastRefreshed = lastRefreshedDate)
    private val teamAndPlayersB = TeamAndPlayers(team = teamB, players = listOf(playerB))

    @Test
    fun toTeamEntity() {
        assertEquals(teamA, teamDtoA.toTeamEntity(teamA.lastRefreshed))
        assertEquals(teamB, teamDtoB.toTeamEntity(teamB.lastRefreshed))
    }

    @Test
    fun toTeamAndPlayersEntity() {
        assertEquals(teamAndPlayersA, teamDtoA.toTeamAndPlayersEntity(teamAndPlayersA.team.lastRefreshed))
        assertEquals(teamAndPlayersB, teamDtoB.toTeamAndPlayersEntity(teamAndPlayersB.team.lastRefreshed))
    }

    @Test
    fun listToTeamAndPlayersEntity() {
        val teamDtos = listOf(teamDtoA, teamDtoB)
        val teamAndPlayersList = listOf(teamAndPlayersA, teamAndPlayersB)
        assertEquals(teamAndPlayersList, teamDtos.toTeamAndPlayersEntity(lastRefreshedDate))
    }
}
