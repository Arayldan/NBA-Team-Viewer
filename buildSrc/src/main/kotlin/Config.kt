@file:Suppress("unused")

object ApplicationId {
    const val id = "ca.cuvillon.nbateamviewer"
}

object Modules {
    const val app = ":app"
    const val navigation = ":navigation"
    const val common = ":common"
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
    const val ktlint = "0.34.2"
    const val navigation = "2.0.0"
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
    const val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinExtensions = "kotlin-android-extensions"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
}

object Libraries {
    // TIMBER
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    // KTLINT
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
}

object KotlinLibraries {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
}

object AndroidLibraries {
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val core = "androidx.core:core-ktx:${Versions.androidXCore}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val navigation = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationFrag = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
}

object TestLibraries {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
