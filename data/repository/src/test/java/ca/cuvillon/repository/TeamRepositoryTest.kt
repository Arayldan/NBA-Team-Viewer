package ca.cuvillon.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import ca.cuvillon.commonTest.datasets.TeamAndPlayersDataSet
import ca.cuvillon.commonTest.datasets.TeamDataSet
import ca.cuvillon.local.dao.TeamDao
import ca.cuvillon.local.dao.TeamPlayersDao
import ca.cuvillon.model.entities.Team
import ca.cuvillon.model.entities.TeamAndPlayers
import ca.cuvillon.remote.TeamDataSource
import ca.cuvillon.repository.utils.DateNow
import ca.cuvillon.repository.utils.Resource
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException
import java.util.Date

@ExperimentalCoroutinesApi
class TeamRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var observerTeamList: Observer<Resource<List<Team>>>
    private lateinit var observerTeamAndPlayers: Observer<Resource<TeamAndPlayers>>
    private lateinit var dateNow: DateNow
    private lateinit var teamRepository: TeamRepository
    private val teamService = mockk<TeamDataSource>()
    private val teamDao = mockk<TeamDao>(relaxed = true)
    private val teamPlayersDao = mockk<TeamPlayersDao>(relaxed = true)

    @Before
    fun setUp() {
        observerTeamList = mockk(relaxed = true)
        observerTeamAndPlayers = mockk(relaxed = true)
        dateNow = mockk()
        teamRepository = TeamRepositoryImpl(teamService, teamDao, teamPlayersDao, dateNow)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Get teams from server when no internet is available`() {
        val exception = IOException("No Internet")
        coEvery { teamService.getTeams() } throws exception
        coEvery { teamDao.getAllOrderedByName() } returns emptyList()

        runBlocking {
            teamRepository.getTeams(true, Team.OrderBy.Name).observeForever(observerTeamList)
        }

        verifyOrder {
            observerTeamList.onChanged(Resource.Loading(null)) // Init loading without data
            observerTeamList.onChanged(Resource.Loading(emptyList())) // Then trying to load from db
            observerTeamList.onChanged(Resource.Error(exception, emptyList())) // Retrofit no internet error
        }
        confirmVerified(observerTeamList)
    }

    @Test
    fun `Get teams from db`() {
        val teamsDto = TeamAndPlayersDataSet.teamDtos
        val teams = TeamDataSet.teams
        every { dateNow.get() } returns Date()
        coEvery { teamService.getTeams() } returns teamsDto
        coEvery { teamDao.getAllOrderedByName() } returns teams
        // Simulate fresh data to avoid a refresh
        coEvery { teamDao.getMinimalLastRefreshed() } returns Date()

        runBlocking {
            teamRepository.getTeams(false, Team.OrderBy.Name).observeForever(observerTeamList)
        }

        verifyOrder {
            observerTeamList.onChanged(Resource.Loading(null)) // Loading from remote source
            observerTeamList.onChanged(Resource.Success(teams)) // Success
        }

        confirmVerified(observerTeamList)
    }

    @Test
    fun `Get team and its players from db`() {
        val teamAndPlayers = TeamAndPlayersDataSet.teamAndPlayersA
        every { dateNow.get() } returns teamAndPlayers.team.lastRefreshed
        coEvery { teamPlayersDao.getTeamAndPlayersForTeam(teamAndPlayers.team.id) } returns teamAndPlayers
        // Simulate fresh data to avoid a refresh
        coEvery { teamDao.getMinimalLastRefreshed() } returns Date()

        runBlocking {
            teamRepository.getTeamAndPlayers(teamAndPlayers.team.id).observeForever(observerTeamAndPlayers)
        }

        verifyOrder {
            observerTeamAndPlayers.onChanged(Resource.Loading(null)) // Loading from remote source
            observerTeamAndPlayers.onChanged(Resource.Success(teamAndPlayers)) // Success
        }

        confirmVerified(observerTeamAndPlayers)
    }
}
