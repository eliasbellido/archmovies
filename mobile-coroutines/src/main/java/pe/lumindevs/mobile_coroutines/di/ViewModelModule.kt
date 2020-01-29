package pe.lumindevs.mobile_coroutines.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pe.lumindevs.mobile_coroutines.ui.details.movie.MovieDetailViewModel
import pe.lumindevs.mobile_coroutines.ui.details.person.PersonDetailViewModel
import pe.lumindevs.mobile_coroutines.ui.details.tv.TvDetailViewModel
import pe.lumindevs.mobile_coroutines.ui.main.MainActivityViewModel

val viewModelModule = module {
    viewModel { MainActivityViewModel(get(), get()) }
    viewModel { MovieDetailViewModel(get()) }
    viewModel { TvDetailViewModel(get()) }
    viewModel { PersonDetailViewModel(get())}
}