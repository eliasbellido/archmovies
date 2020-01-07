package pe.lumindevs.archmovies.repo

import androidx.lifecycle.MutableLiveData
import pe.lumindevs.archmovies.entity.database.PeopleDao
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.response.PersonDetail
import pe.lumindevs.archmovies.network.ApiResponse
import pe.lumindevs.archmovies.network.client.PeopleClient
import pe.lumindevs.archmovies.network.message
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    private val peopleClient: PeopleClient,
    private val peopleDao: PeopleDao
) : Repository {

    override var isLoading = false

    init {
        Timber.d("Injection PeopleRepository")
    }

    fun loadPeople(page: Int, error: (String) -> Unit): MutableLiveData<List<Person>> {
        val liveData = MutableLiveData<List<Person>>()
        var people = peopleDao.getPeople(page)
        if(people.isEmpty()){
            isLoading = true
            peopleClient.fetchPopularPeople(page) { response ->
                isLoading = false
                when (response) {
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
        liveData.postValue(people)
        return liveData
    }

    fun loadPersonDetail(id: Int, error: (String) -> Unit): MutableLiveData<PersonDetail>{
        val liveData = MutableLiveData<PersonDetail>()
        val person = peopleDao.getPerson(id)
        var personDetail = person.personDetail
        if(personDetail == null){
            isLoading = true
            peopleClient
                .fetchPersonDetail(id) { response ->
                    isLoading = false
                    when(response) {
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
        liveData.postValue(person.personDetail)
        return liveData
    }

    fun getPerson(id: Int) = peopleDao.getPerson(id)


}