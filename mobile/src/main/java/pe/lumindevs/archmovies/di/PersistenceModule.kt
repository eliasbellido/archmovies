package pe.lumindevs.archmovies.di

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import dagger.Module
import dagger.Provides
import pe.lumindevs.archmovies.entity.R
import pe.lumindevs.archmovies.entity.database.AppDatabase
import pe.lumindevs.archmovies.entity.database.MovieDao
import pe.lumindevs.archmovies.entity.database.PeopleDao
import pe.lumindevs.archmovies.entity.database.TvDao
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabase(@NonNull application: Application): AppDatabase{
        return Room
            .databaseBuilder(application, AppDatabase::class.java,
                application.getString(R.string.database))
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieDao(@NonNull database: AppDatabase) : MovieDao{
        return database.movieDao()
    }

    @Provides
    @Singleton
    fun providesTvDao(@NonNull database: AppDatabase): TvDao {
        return database.tvDao()
    }

    @Provides
    @Singleton
    fun providesPeopleDao(@NonNull database: AppDatabase): PeopleDao {
        return database.peopleDao()
    }
}