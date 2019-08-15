package ca.cuvillon.teamlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.filters.SmallTest
import ca.cuvillon.common.utils.Event
import ca.cuvillon.commonTest.datasets.TeamDataSet
import ca.cuvillon.commonTest.utilities.getValue
import ca.cuvillon.model.entities.Team
import ca.cuvillon.navigation.NavigationCommand
import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.repository.utils.Resource
import ca.cuvillon.teamlist.domain.GetTeamsUseCase
import io.mockk.coEvery
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
@SmallTest
class TeamListUnitTests {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getTeamsUseCase: GetTeamsUseCase
    private lateinit var teamListViewModel: TeamListViewModel
    private val appDispatchers = AppDispatchers(Dispatchers.Unconfined, Dispatchers.Unconfined)

    @Before
    fun setUp() {
        getTeamsUseCase = mockk()
    }

    @Test
    fun `Teams requested when ViewModel is created`() {
        val observer = mockk<Observer<Resource<List<Team>>>>(relaxed = true)
        val result = Resource.Success(TeamDataSet.teams)
        coEvery {
            getTeamsUseCase(
                false,
                Team.OrderBy.Name
            )
        } returns MutableLiveData<Resource<List<Team>>>().apply { value = result }

        teamListViewModel = TeamListViewModel(getTeamsUseCase, appDispatchers)
        teamListViewModel.teams.observeForever(observer)

        verify {
            observer.onChanged(result)
        }

        confirmVerified(observer)
    }

    @Test
    fun `Teams requested but failed when ViewModel is created`() {
        val observer = mockk<Observer<Resource<List<Team>>>>(relaxed = true)
        val observerSnackbar = mockk<Observer<Event<Int>>>(relaxed = true)
        val result = Resource.Error(Exception("fail"), null)
        coEvery {
            getTeamsUseCase(
                any(),
                Team.OrderBy.Name
            )
        } returns MutableLiveData<Resource<List<Team>>>().apply { value = result }

        teamListViewModel = TeamListViewModel(getTeamsUseCase, appDispatchers)
        teamListViewModel.teams.observeForever(observer)
        teamListViewModel.snackBarError.observeForever(observerSnackbar)

        verify {
            observer.onChanged(result)
            observerSnackbar.onChanged(teamListViewModel.snackBarError.value)
        }

        confirmVerified(observer)
    }

    @Test
    fun `User clicks on item on RecyclerView`() {
        val teamId = TeamDataSet.teams.first().id
        val navDirections = TeamListFragmentDirections.actionTeamListFragmentToTeamDetailFragment(teamId)
        val event = Event(NavigationCommand.To(navDirections))
        coEvery {
            getTeamsUseCase(false, Team.OrderBy.Name)
        } returns MutableLiveData<Resource<List<Team>>>().apply {
            value = Resource.Success(TeamDataSet.teams)
        }

        teamListViewModel = TeamListViewModel(getTeamsUseCase, appDispatchers)
        teamListViewModel.clickOnItem(TeamDataSet.teams.first())

        Assert.assertEquals(event.peekContent(), getValue(teamListViewModel.navigation).peekContent())
    }

    @Test
    fun `User refreshes list with swipe to refresh`() {
        val observer = mockk<Observer<Resource<List<Team>>>>(relaxed = true)
        val result = Resource.Success(TeamDataSet.teams)
        coEvery {
            getTeamsUseCase(
                any(),
                Team.OrderBy.Name
            )
        } returns MutableLiveData<Resource<List<Team>>>().apply { value = result }

        teamListViewModel = TeamListViewModel(getTeamsUseCase, appDispatchers)
        teamListViewModel.teams.observeForever(observer)
        teamListViewModel.refreshTeams()

        verify(exactly = 2) {
            observer.onChanged(result) // When VM is created
            observer.onChanged(result) // When user actually refreshes
        }

        confirmVerified(observer)
    }
}
