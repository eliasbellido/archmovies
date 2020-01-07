package pe.lumindevs.archmovies.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityModule::class,
    BaseModule::class,
    ViewModelModule::class,
    NetworkModule::class,
    PersistenceModule::class
])
interface AppComponent : AndroidInjector<DaggerApplication>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder

        fun build(): AppComponent
    }

    override fun inject(instance: DaggerApplication?)
}