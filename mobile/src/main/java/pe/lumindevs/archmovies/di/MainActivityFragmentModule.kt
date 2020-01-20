package pe.lumindevs.archmovies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.lumindevs.archmovies.ui.main.MovieListFragment
import pe.lumindevs.archmovies.ui.main.PersonListFragment
import pe.lumindevs.archmovies.ui.main.TvListFragment

@Module
abstract class MainActivityFragmentModule{

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment() : MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeTvListFragment(): TvListFragment

    @ContributesAndroidInjector
    abstract fun contributePersonlistFragment(): PersonListFragment


}