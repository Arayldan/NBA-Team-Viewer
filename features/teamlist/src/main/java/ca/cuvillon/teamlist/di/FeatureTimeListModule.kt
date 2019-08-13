package ca.cuvillon.teamlist.di

import ca.cuvillon.teamlist.TeamListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureTeamListModule = module {
    viewModel { TeamListViewModel() }
}
