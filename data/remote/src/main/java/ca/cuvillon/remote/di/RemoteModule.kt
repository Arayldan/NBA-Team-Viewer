package ca.cuvillon.remote.di

import ca.cuvillon.remote.TeamDataSource
import ca.cuvillon.remote.TeamService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

fun createRemoteModule(baseUrl: String) = module {
    factory<Interceptor> {
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.HEADERS
        }
    }

    factory { OkHttpClient.Builder().addInterceptor(get<Interceptor>()).build() }

    factory<Converter.Factory> { GsonConverterFactory.create() }

    single {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(get())
            .addConverterFactory(get())
            .build()
    }

    factory<TeamService> { get<Retrofit>().create() }

    factory { TeamDataSource(get()) }
}
