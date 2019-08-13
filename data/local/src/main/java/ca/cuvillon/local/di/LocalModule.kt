package ca.cuvillon.local.di

import ca.cuvillon.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    factory { get<AppDatabase>().teamDao() }
    factory { get<AppDatabase>().playerDao() }
}
