package pe.lumindevs.mobile_coroutines

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pe.lumindevs.mobile_coroutines.di.networkModule
import pe.lumindevs.mobile_coroutines.di.persistenceModule
import pe.lumindevs.mobile_coroutines.di.repositoryModule
import pe.lumindevs.mobile_coroutines.di.viewModelModule
import timber.log.Timber

class Application : Application(){

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        startKoin {
            androidContext(this@Application)
            modules(viewModelModule)
            modules(persistenceModule)
            modules(repositoryModule)
            modules(networkModule)
        }

        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }
}