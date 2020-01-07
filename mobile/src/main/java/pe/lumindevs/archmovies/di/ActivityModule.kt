package pe.lumindevs.archmovies.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import pe.lumindevs.archmovies.ui.main.MainActivity

@Module
abstract class ActivityModule{

    @ContributesAndroidInjector//(modules = [MainActivityFragmentModule::class])
    internal abstract fun contributeMainActivity(): MainActivity
}