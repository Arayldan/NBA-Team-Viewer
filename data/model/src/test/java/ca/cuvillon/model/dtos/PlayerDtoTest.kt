package ca.cuvillon.model.dtos

import ca.cuvillon.model.entities.Player
import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerDtoTest {

    private val playerA = Player(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10, teamId = 1)
    private val playerDtoA = PlayerDto(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10)

    private val playerB = Player(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20, teamId = 1)
    private val playerDtoB = PlayerDto(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20)

    private val players = listOf(playerA, playerB)
    private val playerDtos = listOf(playerDtoA, playerDtoB)

    @Test
    fun toEntity() {
        assertEquals(playerA, playerDtoA.toEntity(playerA.teamId))
        assertEquals(playerB, playerDtoB.toEntity(playerB.teamId))
    }

    @Test
    fun listToEntity() {
        assertEquals(players, playerDtos.toEntity(players.first().teamId))
    }
}
