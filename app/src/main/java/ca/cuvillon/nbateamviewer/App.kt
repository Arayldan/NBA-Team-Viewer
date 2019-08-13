package ca.cuvillon.nbateamviewer

import android.app.Application
import android.os.StrictMode
import ca.cuvillon.nbateamviewer.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureTimber()
        configureStrictMode()
        configureDi()
    }

    private fun provideComponent(): List<Module> {
        return appComponent
    }

    private fun configureTimber() {
        Timber.uprootAll()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun configureStrictMode() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )

            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }
    }

    private fun configureDi() {
        startKoin {
            if (BuildConfig.DEBUG) {
                // Use Koin Android Logger
                androidLogger()
            }
            // Declare Android context
            androidContext(this@App)
            // Declare modules to use
            modules(provideComponent())
        }
    }
}
