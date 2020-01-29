package pe.lumindevs.mobile_coroutines.di

import org.koin.dsl.module
import pe.lumindevs.mobile_coroutines.repository.DiscoverRepository
import pe.lumindevs.mobile_coroutines.repository.MovieRepository
import pe.lumindevs.mobile_coroutines.repository.PeopleRepository
import pe.lumindevs.mobile_coroutines.repository.TvRepository

val repositoryModule = module {
    single { DiscoverRepository(get(), get(), get()) }
    single { PeopleRepository(get(), get()) }
    single { MovieRepository(get(), get()) }
    single { TvRepository(get(), get()) }
}