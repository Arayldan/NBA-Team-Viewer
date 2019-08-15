package ca.cuvillon.common_test.datasets

import ca.cuvillon.model.entities.TeamAndPlayers

object TeamAndPlayersDataSet {

    val teamAndPlayersA = TeamAndPlayers(team = TeamDataSet.teamA, players = PlayerDataSet.playersA)
    val teamAndPlayersB = TeamAndPlayers(team = TeamDataSet.teamB, players = PlayerDataSet.playersB)
    val teamAndPlayersC = TeamAndPlayers(team = TeamDataSet.teamC, players = PlayerDataSet.playersC)

    val teamDtos = listOf(
        TeamDataSet.teamDtoA,
        TeamDataSet.teamDtoB,
        TeamDataSet.teamDtoC
    )
    val teamAndPlayersList = listOf(
        teamAndPlayersA,
        teamAndPlayersB,
        teamAndPlayersC
    )
}
