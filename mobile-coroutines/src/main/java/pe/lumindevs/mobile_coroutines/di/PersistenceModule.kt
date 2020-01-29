package pe.lumindevs.mobile_coroutines.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import pe.lumindevs.archmovies.entity.database.AppDatabase
import pe.lumindevs.mobile_coroutines.R
import pe.lumindevs.mobile_coroutines.repository.DiscoverRepository

val persistenceModule = module {
    single{
        Room
            .databaseBuilder(androidApplication(), AppDatabase::class.java,
                androidApplication().getString(R.string.database))
            .allowMainThreadQueries()
            .build()
    }

    single { get<AppDatabase>().movieDao() }
    single { get<AppDatabase>().tvDao() }
    single { get<AppDatabase>().peopleDao() }
}