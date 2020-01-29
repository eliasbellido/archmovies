package pe.lumindevs.mobile_coroutines.ui.details.movie

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import pe.lumindevs.archmovies.entity.Keyword
import pe.lumindevs.archmovies.entity.Review
import pe.lumindevs.archmovies.entity.Video
import pe.lumindevs.archmovies.entity.entities.Movie
import pe.lumindevs.mobile_coroutines.base.LiveCoroutinesViewModel
import pe.lumindevs.mobile_coroutines.repository.MovieRepository
import timber.log.Timber

class MovieDetailViewModel constructor(
    private val movieRepository: MovieRepository
) : LiveCoroutinesViewModel(){

    private val movieIdLiveData: MutableLiveData<Int> = MutableLiveData()
    val keywordListLiveData: LiveData<List<Keyword>>
    val videoListLiveData: LiveData<List<Video>>
    val reviewListLiveData: LiveData<List<Review>>
    val toastLiveData: MutableLiveData<String> = MutableLiveData()

    private lateinit var movie: Movie
    val favourite = ObservableBoolean()

    init {
        Timber.d("Injection MovieDetailViewModel")

        keywordListLiveData = movieIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                movieRepository.loadKeywordList(id) {toastLiveData.postValue(it) }
            }

        }

        videoListLiveData = movieIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                movieRepository.loadVideoList(id) { toastLiveData.postValue(it) }
            }
        }

        reviewListLiveData = movieIdLiveData.switchMap { id ->
            launchOnViewModelScope {
                movieRepository.loadReviewList(id) { toastLiveData.postValue(it) }
            }
        }
    }

    fun postMovieId(id: Int){
        movieIdLiveData.postValue(id)
        movie = movieRepository.getMovie(id)
        favourite.set(movie.favourite)
    }

    fun getMovie() = movie

    fun onClickedFavourite(movie: Movie) =
        favourite.set(movieRepository.onClickFavourite(movie))

}