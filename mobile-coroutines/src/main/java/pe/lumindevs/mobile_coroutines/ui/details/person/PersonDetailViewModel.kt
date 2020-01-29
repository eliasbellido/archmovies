package pe.lumindevs.mobile_coroutines.ui.details.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.response.PersonDetail
import pe.lumindevs.mobile_coroutines.base.LiveCoroutinesViewModel
import pe.lumindevs.mobile_coroutines.repository.PeopleRepository
import timber.log.Timber

class PersonDetailViewModel constructor(
    private val peopleRepository: PeopleRepository
) : LiveCoroutinesViewModel() {

    private val personIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val personLiveData: LiveData<PersonDetail>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    private lateinit var person : Person

    init {
        Timber.d("Koin Injection PersonDetailViewModel")

        personLiveData = personIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                peopleRepository.loadPersonDetail(id) {toastLiveData.postValue(it) }
            }
        }
    }

    fun postPersonId(id: Int) {
        personIdLiveData.postValue(id)
        person = peopleRepository.getPerson(id)
    }

    fun getPerson() = person
}