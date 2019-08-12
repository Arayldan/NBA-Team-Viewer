package ca.cuvillon.nbateamviewer

import android.app.Application
import android.os.StrictMode
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        configureTimber()
        configureStrictMode()
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
}
