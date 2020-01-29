package pe.lumindevs.mobile_coroutines.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.mobile_coroutines.base.LiveCoroutinesViewModel
import pe.lumindevs.mobile_coroutines.repository.DiscoverRepository
import pe.lumindevs.mobile_coroutines.repository.PeopleRepository
import timber.log.Timber

class MainActivityViewModel constructor(
    private val discoverRepository: DiscoverRepository,
    private val peopleRepository: PeopleRepository
) : LiveCoroutinesViewModel() {

    private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
    val movieListLiveData: LiveData<List<Movie>>

    private var tvPageLiveData: MutableLiveData<Int> = MutableLiveData()
    val tvListLiveData: LiveData<List<Tv>>

    private var peoplePageLiveData: MutableLiveData<Int> = MutableLiveData()
    val peopleLiveData: LiveData<List<Person>>

    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init {
        Timber.d("Koin Injection MainActivityViewModel")

        movieListLiveData = moviePageLiveData.switchMap { page ->
            launchOnViewModelScope {
                discoverRepository.loadMovies(page) { toastLiveData.postValue(it)}
            }
        }

        tvListLiveData = tvPageLiveData.switchMap { page ->
            launchOnViewModelScope {
                discoverRepository.loadTvs(page) { toastLiveData.postValue(it)}
            }
        }

        peopleLiveData = peoplePageLiveData.switchMap { page ->
            launchOnViewModelScope {
                peopleRepository.loadPeople(page) { toastLiveData.postValue(it)}
            }
        }
    }

    fun postMoviePage(page: Int) = moviePageLiveData.postValue(page)

    fun postTvPage(page: Int) = tvPageLiveData.postValue(page)

    fun postPeoplePage(page: Int) = peoplePageLiveData.postValue(page)

    fun getFavouriteMovieList() = discoverRepository.getFavouriteMovieList()

    fun getFavouriteTvList() = discoverRepository.getFavouriteTvList()

    fun isLoading() = discoverRepository.isLoading || peopleRepository.isLoading
}