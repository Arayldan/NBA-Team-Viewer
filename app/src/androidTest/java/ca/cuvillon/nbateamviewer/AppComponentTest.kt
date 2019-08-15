package ca.cuvillon.nbateamviewer

import androidx.test.platform.app.InstrumentationRegistry
import ca.cuvillon.nbateamviewer.di.appComponent
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.check.checkModules

@RunWith(JUnit4::class)
class AppComponentTest {

    /**
     * Check if every modules are well configured with all definitions bounded.
     */
    @Test
    fun testCheckModules() {
        koinApplication {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(appComponent)
        }.checkModules()
    }
}
