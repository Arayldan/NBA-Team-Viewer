package ca.cuvillon.repository.di

import ca.cuvillon.repository.AppDispatchers
import ca.cuvillon.repository.TeamRepository
import ca.cuvillon.repository.TeamRepositoryImpl
import ca.cuvillon.repository.utils.DateNow
import ca.cuvillon.repository.utils.DateNowImpl
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory<DateNow> { DateNowImpl() }
    factory<TeamRepository> { TeamRepositoryImpl(get(), get(), get(), get()) }
}
