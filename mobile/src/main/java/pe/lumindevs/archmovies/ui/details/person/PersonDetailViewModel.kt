package pe.lumindevs.archmovies.ui.details.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.response.PersonDetail
import pe.lumindevs.archmovies.repo.PeopleRepository
import timber.log.Timber
import javax.inject.Inject

class PersonDetailViewModel @Inject constructor(private val peopleRepository: PeopleRepository)
    : ViewModel() {

    private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val personLiveData: LiveData<PersonDetail>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    private lateinit var person: Person

    init {
        Timber.d("Injection: PersonDetailViewModel")

        personLiveData = personIdLiveData.switchMap { id ->
            peopleRepository.loadPersonDetail(id) { toastLiveData.postValue(it) }
        }
    }

    fun postPersonId(id: Int){
        personIdLiveData.postValue(id)
        person = peopleRepository.getPerson(id)
    }

    fun getPerson() = person
}