package ca.cuvillon.local.di

import ca.cuvillon.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single { AppDatabase.buildDatabase(androidContext()) }
    factory { (get() as AppDatabase).teamDao() }
    factory { (get() as AppDatabase).playerDao() }
}
