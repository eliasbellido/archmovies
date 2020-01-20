package pe.lumindevs.archmovies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.lumindevs.archmovies.base.ViewModelActivity
import pe.lumindevs.archmovies.base.ViewModelFragment

@Module
abstract class BaseModule {

    @ContributesAndroidInjector
    internal abstract fun contributeViewModelActivity(): ViewModelActivity

    @ContributesAndroidInjector
    internal abstract fun contributeViewModelFragment(): ViewModelFragment
}