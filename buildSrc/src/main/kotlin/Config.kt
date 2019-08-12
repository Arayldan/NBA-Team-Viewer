@file:Suppress("unused")

object ApplicationId {
    const val id = "ca.cuvillon.nbateamviewer"
}

object Modules {
    const val app = ":app"
}

object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object Versions {
    const val kotlin = "1.3.41"
    const val gradle = "3.4.1"
    const val appCompat = "1.0.2"
    const val androidXCore = "1.0.2"
    const val constraintLayout = "1.1.3"
    const val junit = "4.12"
    const val testRunner = "1.2.0"
    const val espresso = "3.2.0"
    const val timber = "4.7.1"
}

object Config {
    const val minSdk = 24
    const val targetSdk = 28
    const val compileSdk = 28
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object ClassPaths {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinExtensions = "kotlin-android-extensions"
}

object Libraries {
    // TIMBER
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val core = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
