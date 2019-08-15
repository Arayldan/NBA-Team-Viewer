package ca.cuvillon.teamdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import ca.cuvillon.common.utils.Event
import ca.cuvillon.common.utils.getValue
import ca.cuvillon.commonTest.datasets.TeamAndPlayersDataSet
import ca.cuvillon.model.entities.TeamAndPlayers
import ca.cuvillon.navigation.NavigationCommand
import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.repository.utils.Resource
import ca.cuvillon.teamdetail.domain.GetTeamDetailUseCase
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class TeamDetailUnitTests {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getTeamDetailUseCase: GetTeamDetailUseCase
    private lateinit var viewModel: TeamDetailViewModel
    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        getTeamDetailUseCase = mockk()
        viewModel = TeamDetailViewModel(getTeamDetailUseCase, appDispatchers)
    }

    @Test
    fun `Team detail requested when ViewModel is created`() {
        val observerResult = mockk<Observer<TeamAndPlayers>>(relaxed = true)
        val loading = Resource.Loading(null)
        val result = Resource.Success(TeamAndPlayersDataSet.teamAndPlayersA)
        val events = MutableLiveData<Resource<TeamAndPlayers>>().apply { value = loading }

        coEvery { getTeamDetailUseCase(TeamAndPlayersDataSet.teamAndPlayersA.team.id) } returns events

        viewModel.teamAndPlayers.observeForever(observerResult)
        viewModel.loadData(TeamAndPlayersDataSet.teamAndPlayersA.team.id)

        events.value = result

        verify {
            observerResult.onChanged(TeamAndPlayersDataSet.teamAndPlayersA)
        }

        confirmVerified(observerResult)
    }

    @Test
    fun `Team detail requested but failed when ViewModel is created`() {
        val observerResult = mockk<Observer<TeamAndPlayers>>(relaxed = true)
        val observerSnackbar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.Error(Exception("fail"), null)

        coEvery {
            getTeamDetailUseCase(any())
        } returns MutableLiveData<Resource<TeamAndPlayers>>().apply { value = result }

        viewModel.teamAndPlayers.observeForever(observerResult)
        viewModel.snackBarError.observeForever(observerSnackbar)
        viewModel.loadData(TeamAndPlayersDataSet.teamAndPlayersA.team.id)

        verify {
            observerSnackbar.onChanged(viewModel.snackBarError.value)
        }

        confirmVerified(observerResult)
    }

    @Test
    fun `Team detail requested but could not be found`() {
        val observerResult = mockk<Observer<TeamAndPlayers>>(relaxed = true)
        val observerSnackbar = mockk<Observer<Event<Int>>>(relaxed = true)
        val event = Event(NavigationCommand.Back)
        val result = Resource.Success(null)

        coEvery {
            getTeamDetailUseCase(any())
        } returns MutableLiveData<Resource<TeamAndPlayers>>().apply { value = result }

        viewModel.teamAndPlayers.observeForever(observerResult)
        viewModel.snackBarError.observeForever(observerSnackbar)
        viewModel.loadData(TeamAndPlayersDataSet.teamAndPlayersA.team.id)

        verify {
            observerSnackbar.onChanged(viewModel.snackBarError.value)
        }

        confirmVerified(observerResult)
        assertEquals(event.peekContent(), getValue(viewModel.navigation).peekContent())
    }
}
