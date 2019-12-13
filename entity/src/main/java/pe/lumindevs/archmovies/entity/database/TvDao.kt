package pe.lumindevs.archmovies.entity.database

import pe.lumindevs.archmovies.entity.entities.Tv
import androidx.room.*

@Dao
interface TvDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(tvs: List<Tv>)

    @Update
    fun updateTv(tv: Tv)

    @Query("SELECT * FROM Tv WHERE id = :id_")
    fun getTv(id_: Int): Tv

    @Query("SELECT * FROM Tv WHERE page = :page_")
    fun getTvList(page_: Int): List<Tv>

    @Query("SELECT * FROM Tv Where favourite = '1'")
    fun getFavouriteTvList(): List<Tv>
}