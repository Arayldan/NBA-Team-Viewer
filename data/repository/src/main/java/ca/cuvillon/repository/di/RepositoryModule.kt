package ca.cuvillon.repository.di

import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.repository.TeamRepository
import ca.cuvillon.repository.TeamRepositoryImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<TeamRepository> { TeamRepositoryImpl(get(), get(), get()) }
}
