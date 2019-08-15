package ca.cuvillon.common_test.datasets

import ca.cuvillon.model.dtos.TeamDto
import ca.cuvillon.model.entities.Team
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

object TeamDataSet {

    const val UNKNOWN_TEAM_ID = -1

    /**
     * Earliest [Date] object used for tests.
     */
    val earliestDate: Date = GregorianCalendar(2010, Calendar.NOVEMBER, 3, 8, 34, 57).time

    /**
     * Latest [Date] object used for tests.
     */
    val latestDate: Date = Calendar.getInstance().apply {
        time = earliestDate
        add(Calendar.YEAR, 1)
    }.time

    const val TEAM_A_ID = 1 // Avoid circular initialization
    val teamA = Team(id = TEAM_A_ID, name = "A", wins = 0, losses = 3, lastRefreshed = earliestDate)
    val teamDtoA = TeamDto(id = TEAM_A_ID, name = "A", wins = 0, losses = 3, players = PlayerDataSet.playerDtosA)

    const val TEAM_B_ID = 2 // Avoid circular initialization
    val teamB = Team(id = TEAM_B_ID, name = "B", wins = 1, losses = 2, lastRefreshed = latestDate)
    val teamDtoB = TeamDto(id = TEAM_B_ID, name = "B", wins = 1, losses = 2, players = PlayerDataSet.playerDtosB)

    const val TEAM_C_ID = 3 // Avoid circular initialization
    val teamC = Team(id = TEAM_C_ID, name = "C", wins = 2, losses = 1, lastRefreshed = latestDate)
    val teamDtoC = TeamDto(id = TEAM_C_ID, name = "C", wins = 2, losses = 1, players = PlayerDataSet.playerDtosC)
}
