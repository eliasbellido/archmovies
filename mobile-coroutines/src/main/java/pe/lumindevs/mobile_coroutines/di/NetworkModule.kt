package pe.lumindevs.mobile_coroutines.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import pe.lumindevs.archmovies.network.EndPoint
import pe.lumindevs.archmovies.network.RequestInterceptor
import pe.lumindevs.archmovies.network.client.MovieClient
import pe.lumindevs.archmovies.network.client.PeopleClient
import pe.lumindevs.archmovies.network.client.TheDiscoverClient
import pe.lumindevs.archmovies.network.client.TvClient
import pe.lumindevs.archmovies.network.service.MovieService
import pe.lumindevs.archmovies.network.service.PeopleService
import pe.lumindevs.archmovies.network.service.TheDiscoverService
import pe.lumindevs.archmovies.network.service.TvService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(RequestInterceptor())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get<OkHttpClient>())
            .baseUrl(EndPoint.MOVIEDB_HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(TheDiscoverService::class.java) }

    single { TheDiscoverClient(get()) }

    single { get<Retrofit>().create(PeopleService::class.java) }

    single { PeopleClient(get()) }

    single { get<Retrofit>().create(MovieService::class.java) }

    single { MovieClient(get()) }

    single { get<Retrofit>().create(TvService::class.java) }

    single { TvClient(get()) }
}