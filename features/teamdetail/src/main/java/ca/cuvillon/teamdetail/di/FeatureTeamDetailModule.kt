package ca.cuvillon.teamdetail.di

import ca.cuvillon.teamdetail.TeamDetailViewModel
import ca.cuvillon.teamdetail.domain.GetTeamDetailUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureTimeDetailModule = module {
    factory { GetTeamDetailUseCase(get()) }
    viewModel { TeamDetailViewModel(get(), get()) }
}
