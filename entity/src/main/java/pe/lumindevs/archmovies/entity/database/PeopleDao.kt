package pe.lumindevs.archmovies.entity.database

import pe.lumindevs.archmovies.entity.entities.Person
import androidx.room.*

@Dao
interface PeopleDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPeople(people: List<Person>)

    @Update
    fun updatePerson(person: Person)

    @Query("SELECT * FROM People WHERE id = :id_")
    fun getPerson(id_: Int): Person

    @Query("SELECT * FROM People WHERE page = :page_")
    fun getPeople(page_: Int): List<Person>
}