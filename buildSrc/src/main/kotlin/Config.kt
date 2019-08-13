@file:Suppress("unused")

object ApplicationId {
    const val id = "ca.cuvillon.nbateamviewer"
}

object Modules {
    const val app = ":app"

    const val navigation = ":navigation"

    const val common = ":common"

    const val model = ":model"
    const val local = ":local"
    const val remote = ":remote"
    const val repository = ":repository"

    const val featureTeamList = ":teamlist"
}

object ModulePaths {
    const val model = "data/model"
    const val local = "data/local"
    const val remote = "data/remote"
    const val repository = "data/repository"

    const val teamList = "features/teamlist"
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
    const val lifecycle = "2.0.0"
    const val recyclerView = "1.0.0"
    const val koin = "2.0.1"
    const val room = "2.1.0"
    const val gson = "2.8.5"
    const val retrofit = "2.6.1"
    const val okHttp = "4.1.0"
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
    const val kotlin = "kotlin"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinExtensions = "kotlin-android-extensions"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"
    const val kotlinKapt = "kotlin-kapt"
}

object Libraries {
    // TIMBER
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    // KTLINT
    const val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
    // KOIN
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    // RETROFIT
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
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
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    // ROOM
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRunTime = "androidx.room:room-runtime:${Versions.room}"
}

object TestLibraries {
    // ANDROID
    const val junit = "junit:junit:${Versions.junit}"
    const val androidTestRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    // KOIN
    const val koin = "org.koin:koin-test:${Versions.koin}"
}
