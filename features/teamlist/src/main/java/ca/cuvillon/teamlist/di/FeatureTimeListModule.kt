package ca.cuvillon.teamlist.di

import ca.cuvillon.teamlist.TeamListViewModel
import ca.cuvillon.teamlist.domain.GetTeamsUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureTeamListModule = module {
    factory { GetTeamsUseCase(get()) }
    viewModel { TeamListViewModel(get(), get()) }
}
