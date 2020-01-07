package pe.lumindevs.archmovies.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.archmovies.entity.entities.Person
import pe.lumindevs.archmovies.entity.entities.Tv
import pe.lumindevs.archmovies.repo.DiscoverRepository
import pe.lumindevs.archmovies.repo.PeopleRepository
import timber.log.Timber
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val discoverRepository: DiscoverRepository,
    private val peopleRepository: PeopleRepository
): ViewModel(){

    private var moviePageLiveData: MutableLiveData<Int> = MutableLiveData()
    val movieListLiveData: LiveData<List<Movie>>

    private var tvPageLiveData: MutableLiveData<Int> = MutableLiveData()
    val tvListLiveData: LiveData<List<Tv>>

    private var peoplePageLiveData: MutableLiveData<Int> = MutableLiveData()
    val peopleLiveData: LiveData<List<Person>>

    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    init{
        Timber.d("injection MainActivityViewModel")

        this.movieListLiveData = moviePageLiveData.switchMap { page ->
            discoverRepository.loadMovies(page) { toastLiveData.postValue(it)}
        }

        this.tvListLiveData = tvPageLiveData.switchMap { page ->
            discoverRepository.loadTvs(page) { toastLiveData.postValue(it) }
        }

        this.peopleLiveData = peoplePageLiveData.switchMap { page ->
            peopleRepository.loadPeople(page) { toastLiveData.postValue(it) }
        }
    }

    fun postMoviePage(page: Int) = moviePageLiveData.postValue(page)

    fun postTvPage(page: Int) = tvPageLiveData.postValue(page)

    fun postPeoplePage(page: Int) = peoplePageLiveData.postValue(page)

    fun getFavouriteMovieList() = discoverRepository.getFavouriteMovieList()

    fun getFavouriteTvList() = discoverRepository.getFavouriteTvList()

    fun isLoading() = discoverRepository.isLoading || peopleRepository.isLoading
}