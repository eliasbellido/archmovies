package pe.lumindevs.mobile_coroutines.repository

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pe.lumindevs.archmovies.entity.database.PeopleDao
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.response.PersonDetail
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.client.PeopleClient
import pe.lumindevs.archmovies.network.message
import timber.log.Timber

class PeopleRepository constructor(
    private val peopleClient: PeopleClient,
    private val peopleDao: PeopleDao
) : Repository {

    override var isLoading = false

    init {
        Timber.d("Koin Injection PeopleRepository")
    }

    suspend fun loadPeople(page: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO){
            val liveData = MutableLiveData<List<Person>>()
            var people = peopleDao.getPeople(page)
            if(people.isNullOrEmpty()){
                isLoading = true
                peopleClient.fetchPopularPeople(page) { response ->
                    isLoading = false
                    when(response){
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                people = data.results
                                people.forEach { it.page = page }
                                liveData.postValue(people)
                                peopleDao.insertPeople(people)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            liveData.apply { postValue(people) }
        }

    suspend fun loadPersonDetail(id: Int, error: (String) -> Unit) =
        withContext(Dispatchers.IO){
            val liveData = MutableLiveData<PersonDetail>()
            val person = peopleDao.getPerson(id)
            var personDetail = person.personDetail
            if(personDetail == null){
                isLoading = true
                peopleClient.fetchPersonDetail(id) { response ->
                    isLoading = false
                    when(response){
                        is ApiResponse.Success -> {
                            response.data?.let { data ->
                                personDetail = data
                                person.personDetail = personDetail
                                liveData.postValue(personDetail)
                                peopleDao.updatePerson(person)
                            }
                        }
                        is ApiResponse.Failure.Error -> error(response.message())
                        is ApiResponse.Failure.Exception -> error(response.message())
                    }
                }
            }
            liveData.apply { postValue(person.personDetail) }
        }

    fun getPerson(id: Int) = peopleDao.getPerson(id)
}