package pe.lumindevs.archmovies.entity.database

import pe.lumindevs.archmovies.entity.converters.*
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.entities.Tv
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        Movie::class,
        Tv::class,
        Person::class
    ],
    version = 1,
    exportSchema = true)
@TypeConverters(
    value = [
        StringListConverter::class,
        IntegerListConverter::class,
        KeywordListConverter::class,
        VideoListConverter::class,
        ReviewListConverter::class
    ]
)
abstract class AppDatabase: RoomDatabase(){

    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao
    abstract fun peopleDao(): PeopleDao
}