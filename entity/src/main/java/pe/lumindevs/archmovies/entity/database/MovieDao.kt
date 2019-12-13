package pe.lumindevs.archmovies.entity.database

import pe.lumindevs.archmovies.entity.entities.Movie
import androidx.room.*

/** A data access object aobut the [Movie] entity.*/
@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieList(movies: List<Movie>)

    @Update
    fun updateMovie(movie: Movie)

    @Query("SELECT * FROM Movie WHERE id = :id_")
    fun getMovie(id_: Int): Movie

    @Query("SELECT * FROM Movie WHERE page = :page_")
    fun getMovieList(page_: Int): List<Movie>

    @Query("SELECT * FROM Movie WHERE favourite = '1'")
    fun getFavouriteMovieList(): List<Movie>
}