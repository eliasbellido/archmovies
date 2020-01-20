package pe.lumindevs.archmovies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import pe.lumindevs.archmovies.ui.details.movie.MovieDetailViewModel
import pe.lumindevs.archmovies.ui.main.MainActivityViewModel
import pe.lumindevs.archmovies.utils.AppViewModelFactory

@Module
internal abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    internal abstract fun bindMainActivityViewModels(mainActivityViewModel: MainActivityViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    internal abstract fun bindMovieDetailViewModels(movieDetailViewMOdel: MovieDetailViewModel) : ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: AppViewModelFactory) : ViewModelProvider.Factory
}