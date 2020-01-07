package pe.lumindevs.archmovies.di

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import pe.lumindevs.archmovies.network.EndPoint
import pe.lumindevs.archmovies.network.RequestInterceptor
import pe.lumindevs.archmovies.network.client.PeopleClient
import pe.lumindevs.archmovies.network.client.TheDiscoverClient
import pe.lumindevs.archmovies.network.service.PeopleService
import pe.lumindevs.archmovies.network.service.TheDiscoverService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(RequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(EndPoint.MOVIEDB_HOSTNAME)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideDiscoverService(retrofit: Retrofit): TheDiscoverService {
        return retrofit.create(TheDiscoverService::class.java)
    }

    @Provides
    @Singleton
    fun provideDiscoverClient(theDiscoverService: TheDiscoverService) : TheDiscoverClient {
        return TheDiscoverClient(theDiscoverService)
    }

    @Provides
    @Singleton
    fun providePeopleService(retrofit: Retrofit) : PeopleService {
        return retrofit.create(PeopleService::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleClient(peopleService: PeopleService) : PeopleClient {
        return PeopleClient(peopleService)
    }


}