package ca.cuvillon.local.base

import android.content.Context
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import ca.cuvillon.local.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

@RunWith(JUnit4::class)
abstract class BaseDaoTest : KoinTest {

    protected val database: AppDatabase by inject()

    @Before
    open fun setUp() {
        configureDi()
    }

    @After
    open fun tearDown() {
        database.close()
        stopKoin()
    }

    // CONFIGURATION
    private fun configureDi() {
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        startKoin {
            androidContext(context)
            modules(listOf(configureLocalModuleTest(context)))
        }
    }

    private fun configureLocalModuleTest(context: Context) = module {
        single {
            // Use in memory database and allow queries on main thread for testing purposes.
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
        factory { get<AppDatabase>().teamDao() }
        factory { get<AppDatabase>().playerDao() }
        factory { get<AppDatabase>().teamPlayersDao() }
    }
}
