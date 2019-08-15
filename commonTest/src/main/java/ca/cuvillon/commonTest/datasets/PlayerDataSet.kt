package ca.cuvillon.commonTest.datasets

import ca.cuvillon.model.dtos.PlayerDto
import ca.cuvillon.model.entities.Player

object PlayerDataSet {

    val playerA =
        Player(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10, teamId = TeamDataSet.TEAM_A_ID)
    val playerB =
        Player(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20, teamId = TeamDataSet.TEAM_A_ID)
    val playersA = listOf(playerA, playerB)
    val playerDtoA = PlayerDto(id = 1, firstName = "A1", lastName = "A2", position = "G", number = 10)
    val playerDtoB = PlayerDto(id = 2, firstName = "B1", lastName = "B2", position = "F", number = 20)
    val playerDtosA = listOf(playerDtoA, playerDtoB)

    val playerC =
        Player(id = 3, firstName = "C1", lastName = "C2", position = "C", number = 30, teamId = TeamDataSet.TEAM_B_ID)
    val playerD =
        Player(id = 4, firstName = "D1", lastName = "D2", position = "PG", number = 40, teamId = TeamDataSet.TEAM_B_ID)
    val playersB = listOf(playerC, playerD)
    val playerDtoC = PlayerDto(id = 3, firstName = "C1", lastName = "C2", position = "C", number = 30)
    val playerDtoD = PlayerDto(id = 4, firstName = "D1", lastName = "D2", position = "PG", number = 40)
    val playerDtosB = listOf(playerDtoC, playerDtoD)

    val playerE =
        Player(id = 5, firstName = "E1", lastName = "E2", position = "X", number = 51, teamId = TeamDataSet.TEAM_C_ID)
    val playerF =
        Player(id = 6, firstName = "F1", lastName = "F2", position = "I", number = 55, teamId = TeamDataSet.TEAM_C_ID)
    val playersC = listOf(playerE, playerF)
    val playerDtoE = PlayerDto(id = 5, firstName = "E1", lastName = "E2", position = "X", number = 51)
    val playerDtoF = PlayerDto(id = 6, firstName = "F1", lastName = "F2", position = "I", number = 55)
    val playerDtosC = listOf(playerDtoE, playerDtoF)
}
