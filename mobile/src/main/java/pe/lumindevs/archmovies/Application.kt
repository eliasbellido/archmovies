package pe.lumindevs.archmovies

import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import pe.lumindevs.archmovies.di.DaggerAppComponent
import timber.log.Timber

class Application: DaggerApplication() {

    private val appComponent = DaggerAppComponent.builder()
        .application(this)
        .build()

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector() = appComponent


}